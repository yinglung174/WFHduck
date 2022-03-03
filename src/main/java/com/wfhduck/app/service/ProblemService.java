package com.wfhduck.app.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.ProblemModel;
import com.wfhduck.app.repository.ProblemRepository;

@Service
public class ProblemService {
	
	@Autowired
	ProblemRepository problemRepository;
	public void addProblem(ProblemModel problemModel){
		problemRepository.addProblem(problemModel);
	}
	
	public String findProblemStatusFromPId(Integer pid) throws SQLException{
		return problemRepository.findProblemStatusFromPId(pid);
	}
	
	public String findProblemCategoryFromPId(Integer pid) throws SQLException{
		return problemRepository.findProblemCategoryFromPId(pid);
	}
		
	public void updateProblemStatus(ProblemModel problemModel){
		problemRepository.updateProblemStatus(problemModel);
	}
	
	public String findProblemCategoryFromUserId(Integer userId) throws SQLException{
		return problemRepository.findProblemCategoryFromUserId(userId);
	}
	
	public String findProblemDescriptionFromPid(Integer pid) throws SQLException{
		return problemRepository.findProblemDescriptionFromPid(pid);
	}
	
	public String findProblemPIdFromUserId(Integer userId) throws SQLException{
		return problemRepository.findProblemPIdFromUserId(userId);
	}
	
	public Integer findProblemUserIdFromPId(Integer pid) throws SQLException{
		return problemRepository.findProblemUserIdFromPId(pid);
	}

}