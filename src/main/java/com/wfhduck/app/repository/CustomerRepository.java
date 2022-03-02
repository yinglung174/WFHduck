package com.wfhduck.app.repository;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
	
	public String findCustomerFullName(String username, String password) throws SQLException{
		try {
			System.out.println("EXCUTE FIND CUSTOMER LOGIN");
			String sql = "SELECT fullName FROM customer WHERE username = ? AND pw = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username,password}, String.class);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public String findCustomerProfile(String username) throws SQLException{
		try {
			System.out.println("EXCUTE FIND CUSTOMER PROFILE");
			String sql = "SELECT username FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
		
	public void updateCustomer(CustomerModel customerModel){
			System.out.println("EXCUTE UPDATE CUSTOMER PROFILE");
		  jdbcTemplate.update("UPDATE customer SET fullName = ? , address = ? WHERE username = ? ",
				 customerModel.getFullName(),customerModel.getAddress(),customerModel.getUsername());
	
	}
	
	public void updateCustomerPassword(CustomerModel customerModel){
		System.out.println("EXCUTE UPDATE CUSTOMER PASSWORD");
	  jdbcTemplate.update("UPDATE customer SET pw = ?  WHERE username = ? ",
			 customerModel.getPassword(), customerModel.getUsername());

}
	
	public String findCustomerPassword(String username) throws SQLException{
		try {
			System.out.println("EXCUTE FIND CUSTOMER PASSWORD");
			String sql = "SELECT pw FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public String findCustomerFullName(String username) throws SQLException{
		try {
			System.out.println("EXCUTE FIND CUSTOMER FULLNAME");
			String sql = "SELECT fullName FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public String findCustomerAddress(String username) throws SQLException{
		try {
			System.out.println("EXCUTE FIND CUSTOMER ADDRESS");
			String sql = "SELECT address FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public Integer findCustomerPoints(String username) throws SQLException{
		try {
			System.out.println("EXCUTE FIND CUSTOMER POINTS");
			String sql = "SELECT points FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			return null;
		}
	}


}