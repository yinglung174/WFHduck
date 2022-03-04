package com.wfhduck.app.repository;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wfhduck.app.model.CustomerModel;

@Repository
public class CustomerRepository {
	
	private static final Logger logger = LogManager.getLogger(CustomerRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addCustomer(CustomerModel customerModel){
		logger.debug("EXCUTE INSERT CUSTOMER");
	  jdbcTemplate.update("INSERT INTO customer(user_id, username, pw, fullName, address, points) "
	  		+ "VALUES (?,?,?,?,?,?)",customerModel.getCId(), customerModel.getUsername(),
	  		customerModel.getPassword(),customerModel.getFullName(),customerModel.getAddress(),customerModel.getPoints());
  }
	
	public String findCustomerFullName(String username, String password) throws SQLException{
		try {
			logger.debug("EXCUTE FIND CUSTOMER LOGIN");
			String sql = "SELECT fullName FROM customer WHERE username = ? AND pw = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username,password}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("LOGIN FAILED: INCORRECT USERNAME OR PASSWORD");
			return null;
		}
	}
	
	public String findCustomerProfile(String username) throws SQLException{
		try {
			logger.debug("EXCUTE FIND CUSTOMER PROFILE");
			String sql = "SELECT username FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH USERNAME FOR USERNAME");
			return null;
		}
	}
		
	public void updateCustomer(CustomerModel customerModel){
			logger.debug("EXCUTE UPDATE CUSTOMER PROFILE");
		  jdbcTemplate.update("UPDATE customer SET fullName = ? , address = ? WHERE username = ? ",
				 customerModel.getFullName(),customerModel.getAddress(),customerModel.getUsername());
	
	}
	
	public void updateCustomerPassword(CustomerModel customerModel){
			logger.debug("EXCUTE UPDATE CUSTOMER PASSWORD");
	  jdbcTemplate.update("UPDATE customer SET pw = ?  WHERE username = ? ",
			 customerModel.getPassword(), customerModel.getUsername());

}
	
	public String findCustomerPassword(String username) throws SQLException{
		try {
			logger.debug("EXCUTE FIND CUSTOMER PASSWORD");
			String sql = "SELECT pw FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH USERNAME FOR PASSWORD");
			return null;
		}
	}
	
	public String findCustomerFullName(String username) throws SQLException{
		try {
			logger.debug("EXCUTE FIND CUSTOMER FULLNAME");
			String sql = "SELECT fullName FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH USERNAME FOR FULLNAME");
			return null;
		}
	}
	
	public String findCustomerAddress(String username) throws SQLException{
		try {
			logger.debug("EXCUTE FIND CUSTOMER ADDRESS");
			String sql = "SELECT address FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH USERNAME FOR ADDRESS");
			return null;
		}
	}
	
	public Integer findCustomerPoints(String username) throws SQLException{
		try {
			logger.debug("EXCUTE FIND CUSTOMER POINTS");
			String sql = "SELECT points FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH USERNAME FOR POINTS");
			return null;
		}
	}
	
	public Integer findCustomerUserId(String username) throws SQLException{
		try {
			logger.debug("EXCUTE FIND CUSTOMER USERID");
			String sql = "SELECT user_id FROM customer WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH USERNAME FOR USERID");
			return null;
		}
	}

	public String findCustomerUsername(Integer userId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND CUSTOMER USERNAME");
			String sql = "SELECT username FROM customer WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{userId}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH UID");
			return null;
		}
	}

}