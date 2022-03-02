package com.wfhduck.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wfhduck.app.model.CustomerModel;
import com.wfhduck.app.service.CustomerService;

@RestController
public class CustomerController {
	
		@Autowired
		CustomerModel customerModel;
		
		@Autowired
		CustomerService customerService;
	    @RequestMapping("/addCustomer")
	    public String hello(){
	    	customerModel = new CustomerModel();
	    	customerModel.setUsername("johnchan");
	    	customerModel.setPassword("johnchan");
	    	customerModel.setFullName("John Chan");
	    	customerModel.setAddress("email@email.com");
	    	customerModel.setPoints(50);
	    	customerService.addCustomer(customerModel);
	        return "New Customer added";
	    }


}