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
@Table(name = "problem")
public class ProblemModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pid")
	private Integer pId;

	@Column(name="category")
	private String category;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private String status;
	
	@Column(name="user_id")
	private Integer userId;

	public Integer getPId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
