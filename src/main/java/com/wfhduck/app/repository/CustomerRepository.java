package com.wfhduck.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wfhduck.app.model.CustomerModel;

@Repository
public class CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addCustomer(CustomerModel customerModel){
		System.out.println("EXCUTE INSERT CUSTOMER");
	  jdbcTemplate.update("INSERT INTO customer(user_id, username, pw, fullName, address, points) "
	  		+ "VALUES (?,?,?,?,?,?)",customerModel.getCId(), customerModel.getUsername(),
	  		customerModel.getPassword(),customerModel.getFullName(),customerModel.getAddress(),customerModel.getPoints());
  }


}