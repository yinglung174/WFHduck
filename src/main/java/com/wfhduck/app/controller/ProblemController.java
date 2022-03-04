package com.wfhduck.app.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wfhduck.app.model.InvoiceModel;
import com.wfhduck.app.model.ProblemModel;
import com.wfhduck.app.service.AddressCoordinateConvertorService;
import com.wfhduck.app.service.CustomerService;
import com.wfhduck.app.service.InvoiceService;
import com.wfhduck.app.service.ProblemService;

@Controller
public class ProblemController {
	
		@Autowired
		ProblemModel problemModel;
		
		@Autowired
		InvoiceModel invoiceModel;
		
		@Autowired
		ProblemService problemService;
		
		@Autowired
		CustomerService customerService;
		
		@Autowired
		InvoiceService invoiceService;
		
		@Autowired
		AddressCoordinateConvertorService addressCoordinateConvertorService;
	
	    
		@RequestMapping("/problemProcess")
	    public String problemProcess(HttpServletRequest request, Model model) throws SQLException{
	    	problemModel = new ProblemModel();
	    	String category = request.getParameter("category");
			String description = request.getParameter("description");
			String username = request.getParameter("username");
			model.addAttribute("username",username);
			model.addAttribute("category",category);
			model.addAttribute("description",description);
			Integer userIdFound = customerService.findCustomerUserId(username);
			if(userIdFound==null) {
				return "index";
			}
	    	problemModel.setCategory(category);
	    	problemModel.setDescription(description);
	    	problemModel.setUserId(userIdFound);
	    	problemService.addProblem(problemModel);
	        return "problemSubmitted";
	    }
		
		@RequestMapping("/readPersonalProblem")
	    public String readPersonalProblem(HttpServletRequest request, Model model) throws SQLException{
	    	problemModel = new ProblemModel();
	    	String username = request.getParameter("username");
			Integer userIdFound = customerService.findCustomerUserId(username);
			Integer points = customerService.findCustomerPoints(username);
			if(userIdFound==null) {
				return "index";
			}
			model.addAttribute("username",username);
			List<ProblemModel> problems = problemService.findAllProblemFromUserId(userIdFound);
			model.addAttribute("problems", problems);
			model.addAttribute("points", points);
	        return "viewPersonalProblem";
	    }
		
		@RequestMapping("/cancelProblem")
	    public String cancelProblem(HttpServletRequest request, Model model) throws SQLException{
	    	problemModel = new ProblemModel();
	    	String username = request.getParameter("username");
	    	Integer problemId = Integer.parseInt(request.getParameter("pid"));
	    	problemModel.setpId(problemId);
	    	problemService.deleteProblem(problemModel);
			Integer userIdFound = customerService.findCustomerUserId(username);
			Integer points = customerService.findCustomerPoints(username);
			model.addAttribute("username",username);
			List<ProblemModel> problems = problemService.findAllProblemFromUserId(userIdFound);
			model.addAttribute("problems", problems);
			model.addAttribute("points", points);
	        return "viewPersonalProblem";
	    }
		
		@RequestMapping("/readOpeningProblem")
	    public String readOpeningProblem(HttpServletRequest request, Model model) throws SQLException{
	    	problemModel = new ProblemModel();
	    	String username = request.getParameter("username");
			model.addAttribute("username",username);
			List<ProblemModel> problems = problemService.findAllProblemFromStatus("Open");
			model.addAttribute("problems", problems);
	        return "viewOpenProblem";
	    }
		
		@RequestMapping("/problemAssignmentProcess")
	    public String problemAssignmentProcess(HttpServletRequest request, Model model) throws SQLException{
	    	problemModel = new ProblemModel();
	    	String username = request.getParameter("username");
	    	Integer pId = Integer.parseInt(request.getParameter("pid"));
			model.addAttribute("username",username);
			Integer userIdFoundFromCustomer = customerService.findCustomerUserId(username);
			Integer userIdFoundFromProblem = problemService.findProblemUserIdFromPId(pId);
			if(userIdFoundFromCustomer == userIdFoundFromProblem) {
				return "problemAssignmentFail";
			}
			problemModel.setpId(pId);
			problemModel.setCategory(problemService.findProblemCategoryFromPId(pId));
			problemModel.setDescription(problemService.findProblemDescriptionFromPid(pId));
			problemModel.setStatus("Handling");
			problemModel.setUserId(userIdFoundFromProblem);
			problemService.updateProblemStatus(problemModel);
			invoiceModel.setCustomerId(userIdFoundFromProblem);
			invoiceModel.setTechnicianId(userIdFoundFromCustomer);
			invoiceModel.setProblemId(pId);
			String addressFoundFromCustomer = customerService.findCustomerAddress(username);
			String usernameFoundFromProblem = customerService.findCustomerUsername(userIdFoundFromProblem);
			String addressFoundFromProblem = customerService.findCustomerAddress(usernameFoundFromProblem);
			Double xFoundFromCustomer = addressCoordinateConvertorService.findAddressCoordinateConvertorX(addressFoundFromCustomer);
			Double yFoundFromCustomer = addressCoordinateConvertorService.findAddressCoordinateConvertorY(addressFoundFromCustomer);
			Double xFoundFromProblem = addressCoordinateConvertorService.findAddressCoordinateConvertorX(addressFoundFromProblem);
			Double yFoundFromProblem = addressCoordinateConvertorService.findAddressCoordinateConvertorY(addressFoundFromProblem);
			Double distance = invoiceModel.calculateDistanceFromCoordinate(xFoundFromCustomer, xFoundFromProblem, yFoundFromCustomer, yFoundFromProblem);
			invoiceModel.setDistance(distance);
			Integer transportFee = invoiceModel.calculateTransportFeeFromDistance(distance);
			invoiceModel.setTransportFee(transportFee);
			invoiceService.addInvoice(invoiceModel);
	        return "problemAssignment";
	    }

}