package com.wfhduck.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.wfhduck.app.model.CustomerModel;
import com.wfhduck.app.service.CustomerService;

@Controller
public class CustomerController {
	
		@Autowired
		CustomerModel customerModel;
		
		@Autowired
		CustomerService customerService;
	    @RequestMapping("/registrationProcess")
	    public String registrationProcess(HttpServletRequest request, Model model){
	    	customerModel = new CustomerModel();
	    	String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullName = request.getParameter("fullName");
			String address = request.getParameter("address");
			model.addAttribute("username",username);
			model.addAttribute("password",password);
			model.addAttribute("fullName",fullName);
			model.addAttribute("address",address);
	    	customerModel.setUsername(username);
	    	customerModel.setPassword(password);
	    	customerModel.setFullName(fullName);
	    	customerModel.setAddress(address);
	    	customerModel.setPoints(50);
	    	customerService.addCustomer(customerModel);
	        return "registrationSuccess";
	    }
	    
	    


}