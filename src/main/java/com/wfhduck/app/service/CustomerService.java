package com.wfhduck.app.service;

import java.sql.SQLException;

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
	
	public String findCustomerFullName(String username, String password) throws SQLException{
		return customerRepository.findCustomerFullName(username,password);
	}
	
	public String findCustomerProfile(String username) throws SQLException{
		return customerRepository.findCustomerProfile(username);
	}
	
	public void updateCustomer(CustomerModel customerModel){
		customerRepository.updateCustomer(customerModel);
	}

}