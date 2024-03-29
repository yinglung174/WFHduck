package com.wfhduck.app.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.CustomerModel;
import com.wfhduck.app.repository.CustomerRepository;
import com.wfhduck.app.repository.CustomerRepositoryAPI;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerRepositoryAPI customerRepositoryAPI;
	
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
	
	public void updateCustomerPoints(CustomerModel customerModel){
		customerRepository.updateCustomerPoints(customerModel);
	}
	
	public void updateCustomerPassword(CustomerModel customerModel){
		customerRepository.updateCustomerPassword(customerModel);
	}
	
	public String findCustomerPassword(String username) throws SQLException{
		return customerRepository.findCustomerPassword(username);
	}
	
	public String findCustomerFullName(String username) throws SQLException{
		return customerRepository.findCustomerFullName(username);
	}
	
	public String findCustomerAddress(String username) throws SQLException{
		return customerRepository.findCustomerAddress(username);
	}
	
	public Integer findCustomerPoints(String username) throws SQLException{
		return customerRepository.findCustomerPoints(username);
	}
	
	public Integer findCustomerUserId(String username) throws SQLException{
		return customerRepository.findCustomerUserId(username);
	}
	
	public String findCustomerUsername(Integer userId) throws SQLException{
		return customerRepository.findCustomerUsername(userId);
	}
	
	public CustomerModel createCustomer(CustomerModel customerModel) throws SQLException{
		return customerRepositoryAPI.save(customerModel);
	}
	public List<CustomerModel> getCustomers() {
	    return customerRepositoryAPI.findAll();
	}
	public void deleteCustomers(Integer userId) {
		customerRepositoryAPI.deleteById(userId);
	}
	public CustomerModel updateCustomers(Integer userId, CustomerModel customerDetails) {
        CustomerModel customerModel = customerRepositoryAPI.findById(userId).get();
        customerModel.setUsername(customerDetails.getUsername());
        customerModel.setPassword(customerDetails.getPassword());
        customerModel.setFullName(customerDetails.getFullName());
        customerModel.setAddress(customerDetails.getAddress());
        customerModel.setPoints(customerDetails.getPoints());
        return customerRepositoryAPI.save(customerModel);                                
	}

}