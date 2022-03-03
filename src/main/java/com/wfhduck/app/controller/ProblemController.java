package com.wfhduck.app.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wfhduck.app.model.ProblemModel;
import com.wfhduck.app.service.CustomerService;
import com.wfhduck.app.service.ProblemService;

@Controller
public class ProblemController {
	
		@Autowired
		ProblemModel problemModel;
		
		@Autowired
		ProblemService problemService;
		
		@Autowired
		CustomerService customerService;
	
	    
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
			if(userIdFound==null) {
				return "index";
			}
			model.addAttribute("username",username);
			List<ProblemModel> problems = problemService.findAllProblemFromUserId(userIdFound);
			model.addAttribute("problems", problems);
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
			problemModel.setStatus("Waiting for Payment");
			problemModel.setUserId(userIdFoundFromProblem);
			problemService.updateProblemStatus(problemModel);
	        return "problemAssignment";
	    }

}