package com.wfhduck.app.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	    	invoiceModel = new InvoiceModel();
	    	String username = request.getParameter("username");
			model.addAttribute("username",username);
			Integer userId = customerService.findCustomerUserId(username);
			List<InvoiceProblemModel> problemsInvoices = invoiceProblemService.findAllInvoiceProblemFromTechnicianId(userId);
			model.addAttribute("problemsInvoices", problemsInvoices);
	        return "viewHandlingProblem";
	    }
		
		

}