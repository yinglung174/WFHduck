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
		CustomerModel technicianModel;
	
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
			customerModel.setUsername(username);
			customerModel.setPoints(points - transportFee); 					
			customerService.updateCustomerPoints(customerModel);	//handling points reduction for customer
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
		
		@RequestMapping("/readAddressProcess")
	    public String readAddressProcess(HttpServletRequest request, Model model) throws SQLException{
	    	String username = request.getParameter("username");
			model.addAttribute("username",username);
			Integer invoiceId = Integer.parseInt(request.getParameter("oid"));
			Integer points = customerService.findCustomerPoints(username);
			Integer customerId = invoiceService.findInoviceCustomerIdFromOId(invoiceId);
			String customerUsername = customerService.findCustomerUsername(customerId);
			String customerAddress = customerService.findCustomerAddress(customerUsername);
			Integer transportFee = invoiceService.findInoviceTransportFeeFromOId(invoiceId);
			Double distance= invoiceService.findInoviceDistanceFromOId(invoiceId);
			model.addAttribute("points",points);
			model.addAttribute("address",customerAddress);
			model.addAttribute("oId",invoiceId);
			model.addAttribute("transportFee",transportFee);
			model.addAttribute("distance",distance);
	        return "viewAddressProcess";
	    }
		
		@RequestMapping("/arriveAddressProcess")
	    public String arriveAddressProcess(HttpServletRequest request, Model model) throws SQLException{
			technicianModel = new CustomerModel();
	    	String username = request.getParameter("username");
			Integer invoiceId = Integer.parseInt(request.getParameter("oid"));
			Integer points = customerService.findCustomerPoints(username);
			Integer transportFee = Integer.parseInt(request.getParameter("transportFee"));
			Integer technicianID = invoiceService.findInoviceTechnicianIdFromOId(invoiceId);
			String technicianUsername = customerService.findCustomerUsername(technicianID);
			technicianModel.setUsername(technicianUsername);
			technicianModel.setPoints(points + transportFee);
			customerService.updateCustomerPoints(technicianModel);	//handling points increase for technician
			invoiceModel.setOId(invoiceId);
			invoiceModel.setStatus("Processing");
			invoiceService.updateInvoiceStatus(invoiceModel);		//updating invoice status to be 'Processing'
			Integer problemId = invoiceService.findInoviceProblemIdFromOId(invoiceId);
			problemModel.setpId(problemId);
			problemModel.setStatus("Arrived");
			problemService.updateProblemStatus(problemModel);		//updating problem status to be 'Arrived'
			model.addAttribute("username",username);
			model.addAttribute("points",points);
	        return "arrivedAddressProcess";
	    }
		
		@RequestMapping("/payServiceProcess")
	    public String payServiceProcess(HttpServletRequest request, Model model) throws SQLException{
	    	String username = request.getParameter("username");
			model.addAttribute("username",username);
			Integer invoiceId = Integer.parseInt(request.getParameter("oid"));
			Integer points = customerService.findCustomerPoints(username);
			model.addAttribute("points",points);
			model.addAttribute("oId",invoiceId);
	        return "submitServiceProcess";
	    }
		
		@RequestMapping("/submitServiceRequestProcess")
	    public String submitServiceRequestProcess(HttpServletRequest request, Model model) throws SQLException{
			technicianModel = new CustomerModel();
			invoiceModel = new InvoiceModel();
	    	String username = request.getParameter("username");
			Integer invoiceId = Integer.parseInt(request.getParameter("oid"));
			Integer points = customerService.findCustomerPoints(username);
			Integer serviceFee = Integer.parseInt(request.getParameter("serviceFee"));
			invoiceModel.setOId(invoiceId);
			invoiceModel.setServiceFee(serviceFee);
			invoiceModel.setStatus("Waiting for confirmation");
			invoiceService.updateInvoiceStatus(invoiceModel);		//updating invoice status to be 'Waiting for confirmation'
			invoiceService.updateInvoiceServiceFee(invoiceModel);	//updating service fee
			Integer problemId = invoiceService.findInoviceProblemIdFromOId(invoiceId);
			problemModel.setpId(problemId);
			problemModel.setStatus("Solved");
			problemService.updateProblemStatus(problemModel);		//updating problem status to be 'Solved'
			model.addAttribute("username",username);
			model.addAttribute("points",points);
	        return "submittedServiceRequestProcess";
	    }
		
		@RequestMapping("/confirmServiceRequestProcess")
	    public String confirmServiceRequestProcess(HttpServletRequest request, Model model) throws SQLException{
	    	String username = request.getParameter("username");
			model.addAttribute("username",username);
			Integer problemId = Integer.parseInt(request.getParameter("pid"));
			Integer points = customerService.findCustomerPoints(username);
			Integer invoiceId = invoiceService.findInoviceOIdFromProblemId(problemId);
			Integer serviceFee = invoiceService.findInoviceServiceFeeFromOId(invoiceId);
			String category = problemService.findProblemCategoryFromPId(problemId);
			String description = problemService.findProblemDescriptionFromPid(problemId);
			model.addAttribute("points",points);
			model.addAttribute("category",category);
			model.addAttribute("description",description);
			model.addAttribute("serviceFee",serviceFee);
			model.addAttribute("oId",invoiceId);
	        return "confirmedServiceRequestProcess";
	    }
		
		@RequestMapping("/payServiceInvoiceProcess")
	    public String payServiceInvoiceProcess(HttpServletRequest request, Model model) throws SQLException{
			customerModel = new CustomerModel();
			technicianModel = new CustomerModel();
	    	String username = request.getParameter("username");
			Integer invoiceId = Integer.parseInt(request.getParameter("oid"));
			Integer points = customerService.findCustomerPoints(username);
			Integer serviceFee = Integer.parseInt(request.getParameter("serviceFee"));
			customerModel.setUsername(username);
			customerModel.setPoints(points - serviceFee); 					
			customerService.updateCustomerPoints(customerModel);	//handling points reduction for customer
			Integer technicianID = invoiceService.findInoviceTechnicianIdFromOId(invoiceId);
			String technicianUsername = customerService.findCustomerUsername(technicianID);
			technicianModel.setUsername(technicianUsername);
			technicianModel.setPoints(points + serviceFee);
			customerService.updateCustomerPoints(technicianModel);	//handling points increase for technician
			invoiceModel.setOId(invoiceId);
			invoiceModel.setStatus("Closed");
			invoiceService.updateInvoiceStatus(invoiceModel);		//updating invoice status to be 'Closed'
			Integer problemId = invoiceService.findInoviceProblemIdFromOId(invoiceId);
			problemModel.setpId(problemId);
			problemModel.setStatus("Closed");
			problemService.updateProblemStatus(problemModel);		//updating problem status to be 'Closed'
			model.addAttribute("username",username);
			model.addAttribute("points",points);
	        return "paidServiceRequestProcess";
	    }
		
		@RequestMapping("/rejectServiceInvoiceProcess")
	    public String rejectServiceInvoiceProcess(HttpServletRequest request, Model model) throws SQLException{
			technicianModel = new CustomerModel();
			invoiceModel = new InvoiceModel();
	    	String username = request.getParameter("username");
			Integer invoiceId = Integer.parseInt(request.getParameter("oid"));
			Integer points = customerService.findCustomerPoints(username);
			invoiceModel.setOId(invoiceId);
			invoiceModel.setServiceFee(0);
			invoiceModel.setStatus("Processing");
			invoiceService.updateInvoiceStatus(invoiceModel);		//updating invoice status to be 'Processing'
			invoiceService.updateInvoiceServiceFee(invoiceModel);	//updating service fee
			Integer problemId = invoiceService.findInoviceProblemIdFromOId(invoiceId);
			problemModel.setpId(problemId);
			problemModel.setStatus("Arrived");
			problemService.updateProblemStatus(problemModel);		//updating problem status to be 'Arrived'
			model.addAttribute("username",username);
			model.addAttribute("points",points);
	        return "interface";
	    }
		
		@RequestMapping("/removeInvoice")
	    public String removeInvoice(HttpServletRequest request, Model model) throws SQLException{
			String username = request.getParameter("username");
	    	problemModel = new ProblemModel();
	    	invoiceModel = new InvoiceModel();
	    	Integer invoiceId = Integer.parseInt(request.getParameter("oid"));
	    	Integer problemId = invoiceService.findInoviceProblemIdFromOId(invoiceId);
	    	invoiceModel.setOId(invoiceId);
	    	invoiceService.deleteInvoice(invoiceModel);
	    	problemModel.setpId(problemId);
			problemModel.setStatus("Open");
			problemService.updateProblemStatus(problemModel);		//updating problem status to be 'Open'
			Integer points = customerService.findCustomerPoints(username);
			model.addAttribute("username",username);
			model.addAttribute("points", points);
			Integer userId = customerService.findCustomerUserId(username);
			List<InvoiceProblemModel> problemsInvoices = invoiceProblemService.findAllInvoiceProblemFromTechnicianId(userId);
			model.addAttribute("problemsInvoices", problemsInvoices);
	        return "viewHandlingProblem"; 
	    }

}