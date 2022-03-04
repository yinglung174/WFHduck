package com.wfhduck.app.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wfhduck.app.model.CustomerModel;
import com.wfhduck.app.model.InvoiceModel;
import com.wfhduck.app.model.InvoiceProblemModel;
import com.wfhduck.app.model.ProblemModel;
import com.wfhduck.app.service.AddressCoordinateConvertorService;
import com.wfhduck.app.service.CustomerService;
import com.wfhduck.app.service.InvoiceProblemService;
import com.wfhduck.app.service.InvoiceService;
import com.wfhduck.app.service.ProblemService;

@Controller
public class InvoiceController {
	
	
		@Autowired
		CustomerModel customerModel;
	
		@Autowired
		ProblemModel problemModel;
		
		@Autowired
		InvoiceModel invoiceModel;
		
		@Autowired
		InvoiceProblemModel invoiceProblemModel;
		
		@Autowired
		ProblemService problemService;
		
		@Autowired
		CustomerService customerService;
		
		@Autowired
		InvoiceService invoiceService;
		
		@Autowired
		InvoiceProblemService invoiceProblemService;
		
		@Autowired
		AddressCoordinateConvertorService addressCoordinateConvertorService;
	
	    
		@RequestMapping("/readHandlingProblem")
	    public String readHandlingProblem(HttpServletRequest request, Model model) throws SQLException{
	    	String username = request.getParameter("username");
			model.addAttribute("username",username);
			Integer userId = customerService.findCustomerUserId(username);
			List<InvoiceProblemModel> problemsInvoices = invoiceProblemService.findAllInvoiceProblemFromTechnicianId(userId);
			model.addAttribute("problemsInvoices", problemsInvoices);
	        return "viewHandlingProblem";
	    }
		
		@RequestMapping("/readInvoiceProcess")
	    public String readInvoiceProcess(HttpServletRequest request, Model model) throws SQLException{
	    	String username = request.getParameter("username");
			model.addAttribute("username",username);
			Integer problemId = Integer.parseInt(request.getParameter("pid"));
			Integer points = customerService.findCustomerPoints(username);
			List<InvoiceModel> invoices = invoiceService.findAllInvoiceFromProblemId(problemId);
			model.addAttribute("invoices",invoices);
			model.addAttribute("points",points);
	        return "viewInvoiceProcess";
	    }
		
		@RequestMapping("/payInvoiceProcess")
	    public String payInvoiceProcess(HttpServletRequest request, Model model) throws SQLException{
			invoiceModel = new InvoiceModel();
			customerModel = new CustomerModel();
	    	String username = request.getParameter("username");
	    	Integer transportFee = Integer.parseInt(request.getParameter("transportFee"));
			Integer invoiceId = Integer.parseInt(request.getParameter("oid"));
			Integer points = customerService.findCustomerPoints(username);
			points = points - transportFee;
			customerModel.setUsername(username);
			customerModel.setPoints(points); 					
			customerService.updateCustomerPoints(customerModel);	//handling points reduction
			invoiceModel.setOId(invoiceId);
			invoiceModel.setStatus("Paid");
			invoiceService.updateInvoiceStatus(invoiceModel);		//updating invoice status to be 'Paid'
			Integer problemId = invoiceService.findInoviceProblemIdFromOId(invoiceId);
			problemModel.setpId(problemId);
			problemModel.setStatus("Arriving");
			problemService.updateProblemStatus(problemModel);		//updating problem status to be 'Arriving'
			model.addAttribute("username",username);
			model.addAttribute("points",points);
	        return "paidInvoiceProcess";
	    }
		
		

}