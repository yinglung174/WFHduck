package com.wfhduck.app.controller;

import java.sql.SQLException;

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
	    
	    @RequestMapping("/profileProcess")
	    public String profileProcess(HttpServletRequest request, Model model) throws SQLException{
	    	customerModel = new CustomerModel();
	    	String username = request.getParameter("username");
			String fullName = request.getParameter("fullName");
			String address = request.getParameter("address");
			String usernameFound = customerService.findCustomerProfile(username);
			if(usernameFound !=null) {
				customerModel.setUsername(username);
		    	customerModel.setFullName(fullName);
		    	customerModel.setAddress(address);
		    	customerService.updateCustomer(customerModel);
			}
			model.addAttribute("username",username);
			model.addAttribute("fullName",fullName);
			model.addAttribute("address",address);
	        return "profileUpdateSuccess";
	    }
	    
	    @RequestMapping("/readCustomerProfile")
	    public String readCustomerProfile(HttpServletRequest request, Model model) throws SQLException{
	    	customerModel = new CustomerModel();
	    	String username = request.getParameter("username");
			String usernameFound = customerService.findCustomerProfile(username);
			String fullNameFound = customerService.findCustomerFullName(username);
			String addressFound = customerService.findCustomerAddress(username);
			model.addAttribute("username",usernameFound);
			model.addAttribute("fullName",fullNameFound);
			model.addAttribute("address",addressFound);
	        return "viewCustomerProfile";
	    }
	    
	    @RequestMapping("/backToInterface")
	    public String backToInterface(HttpServletRequest request, Model model) throws SQLException{
	    	customerModel = new CustomerModel();
	    	String username = request.getParameter("username");
	    	String fullNameFound = customerService.findCustomerFullName(username);
			model.addAttribute("username",username);
			model.addAttribute("fullName",fullNameFound);
	        return "interface";
	    }
	    
	    @RequestMapping("/passwordProcess")
	    public String passwordProcess(HttpServletRequest request, Model model) throws SQLException{
	    	customerModel = new CustomerModel();
	    	String username = request.getParameter("username");
			String password = request.getParameter("password");
			String usernameFound = customerService.findCustomerProfile(username);
			if(usernameFound !=null) {
				customerModel.setUsername(username);
		    	customerModel.setPassword(password);
		    	customerService.updateCustomerPassword(customerModel);
			}
			model.addAttribute("username",username);
			model.addAttribute("password",password);
	        return "profileUpdateSuccess";
	    }
	    
	    @RequestMapping("/changePassword")
	    public String changePassword(HttpServletRequest request, Model model) throws SQLException{
	    	customerModel = new CustomerModel();
	    	String username = request.getParameter("username");
			model.addAttribute("username",username);
	        return "updatePassword";
	    }


}