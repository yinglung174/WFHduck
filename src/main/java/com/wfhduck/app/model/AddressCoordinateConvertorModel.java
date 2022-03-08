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
@Table(name = "addresscoordinateconvertor")
public class AddressCoordinateConvertorModel {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="acc_id")
	private Integer accId;

	@Column(name="address")
	private String address;
	
	@Column(name="coordinate_x")
	private Double x;
	
	@Column(name="coordinate_y")
	private Double y;
	
	private final Double randomMax = 1000.0;
	
	private final Double randomMin = -1000.0;
	
	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}
	
	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
	
	public void randomAssignXY(String address) {
		this.x = randomMin + Math.random() * (randomMax - randomMin);
		this.y = randomMin + Math.random() * (randomMax - randomMin);
	}
}
