package com.wfhduck.app.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wfhduck.app.model.AddressCoordinateConvertorModel;
import com.wfhduck.app.model.CustomerModel;
import com.wfhduck.app.service.AddressCoordinateConvertorService;
import com.wfhduck.app.service.CustomerService;

@Controller
public class CustomerController {
	
		@Autowired
		CustomerModel customerModel;
		
		@Autowired
		AddressCoordinateConvertorModel addresscoordinateconvertorModel;
		
		@Autowired
		CustomerService customerService;
		
		@Autowired
		AddressCoordinateConvertorService addresscoordinateconvertorService;
		
	    @RequestMapping("/registrationProcess")
	    public String registrationProcess(HttpServletRequest request, Model model) throws SQLException{
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
	    	String usernameFound = customerService.findCustomerProfile(username);
	    	if(usernameFound!=null) {
	    		return "index";
	    	}
	    	customerService.addCustomer(customerModel);
	    	addresscoordinateconvertorModel.setAddress(address);
	    	String addressFound = addresscoordinateconvertorService.findAddressCoordinateConvertorAddressFromAddress(address);
	    	if(addressFound==null) {
	    		addresscoordinateconvertorModel.randomAssignXY(addressFound);
	    		addresscoordinateconvertorService.addAddressCoordinateConvertor(addresscoordinateconvertorModel);
	    	}
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