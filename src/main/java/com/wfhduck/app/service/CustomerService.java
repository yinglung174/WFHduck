package com.wfhduck.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.CustomerModel;
import com.wfhduck.app.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	public void addCustomer(CustomerModel customerModel){
		customerRepository.addCustomer(customerModel);
	}

}