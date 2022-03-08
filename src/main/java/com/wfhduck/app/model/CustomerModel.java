package com.wfhduck.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "customer")
public class CustomerModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
	private Integer cId;

	@Column(name="username")
	private String username;
	
	@Column(name="pw")
	private String password;
	
	@Column(name="fullName")
	private String fullName;

	@Column(name="address")
	private String address;
	
	@Column(name="points")
	private Integer points;
	
	public CustomerModel() {
		
	}
	
	public CustomerModel(String username, String password, String fullName, String address, Integer points){
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.address = address;
		this.points = points;
	}

	public Integer getCId() {
		return cId;
	}

	public void setId(Integer cId) {
		this.cId = cId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
}
