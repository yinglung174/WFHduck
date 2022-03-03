package com.wfhduck.app.controller;

import java.sql.SQLException;

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
	    public String registrationProcess(HttpServletRequest request, Model model) throws SQLException{
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

}