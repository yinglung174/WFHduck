package com.wfhduck.app.model;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "invoice")
public class InvoiceModel {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="oid")
	private Integer oId;

	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="technician_id")
	private Integer technicianId;
	
	@Column(name="status")
	private String status;
	
	@Column(name="problem_id")
	private Integer problemId;
	
	@Column(name="transport_fee")
	private Integer transportFee;
	
	@Column(name="service_fee")
	private Integer serviceFee;
	
	@Column(name="distance")
	private Double distance;

	public Integer getOId() {
		return oId;
	}

	public void setOId(Integer oId) {
		this.oId = oId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getTechnicianId() {
		return technicianId;
	}

	public void setTechnicianId(Integer technicianId) {
		this.technicianId = technicianId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
	public Integer getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(Integer transportFee) {
		this.transportFee = transportFee;
	}
	
	public Integer getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Integer serviceFee) {
		this.serviceFee = serviceFee;
	}
	
	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	public Double calculateDistanceFromCoordinate(Double lat1, Double lon1, Double lat2, Double lon2) { //userX, techX, userY, techY
		  Double theta = lon1 - lon2;
		  Double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515; //M
	     // dist = dist * 1.609344; //KM
	      return (double) Math.round(dist);
	}
	
	private Double deg2rad(Double deg) {
	     return (deg * Math.PI / 180.0);
	}

	private Double rad2deg(Double rad) {
	    return (rad * 180.0 / Math.PI);
	}
	
	public Integer calculateTransportFeeFromDistance(Double distance) {
		return (int) Math.round(distance)/10;
	}

}
