package com.wfhduck.app.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.ProblemModel;
import com.wfhduck.app.model.ProblemModel;
import com.wfhduck.app.repository.ProblemRepository;
import com.wfhduck.app.repository.ProblemRepositoryAPI;

@Service
public class ProblemService {
	
	@Autowired
	ProblemRepository problemRepository;
	
	@Autowired
	ProblemRepositoryAPI problemRepositoryAPI;
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
	
	public List<ProblemModel> findAllProblemFromUserId(Integer userId) throws SQLException{
		return problemRepository.findAllProblemFromUserId(userId);
	}
	
	public List<ProblemModel> findAllProblemFromStatus(String status) throws SQLException{
		return problemRepository.findAllProblemFromStatus(status);
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

	public void deleteProblem(ProblemModel problemModel){
		problemRepository.deleteProblem(problemModel);
	}
	public ProblemModel createProblem(ProblemModel problemModel) throws SQLException{
		return problemRepositoryAPI.save(problemModel);
	}
	public List<ProblemModel> getProblems() {
	    return problemRepositoryAPI.findAll();
	}
	public void deleteProblems(Integer pId) {
		problemRepositoryAPI.deleteById(pId);
	}
	public ProblemModel updateProblems(Integer pId, ProblemModel problemDetails) {
        ProblemModel problemModel = problemRepositoryAPI.findById(pId).get();
        problemModel.setCategory(problemDetails.getCategory());
        problemModel.setDescription(problemDetails.getDescription());
        problemModel.setStatus(problemDetails.getStatus());
        problemModel.setUserId(problemDetails.getUserId());
        return problemRepositoryAPI.save(problemModel);                                
	}
}