package com.wfhduck.app.model;

import org.springframework.stereotype.Component;

@Component
public class AddressCoordinateConvertorModel {
	private Integer accId;

	private String address;
	
	private Double x;
	
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
