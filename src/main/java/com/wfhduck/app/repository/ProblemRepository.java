package com.wfhduck.app.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wfhduck.app.model.ProblemModel;

@Repository
public class ProblemRepository {
	
	private static final Logger logger = LogManager.getLogger(ProblemRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addProblem(ProblemModel problemModel){
		logger.debug("EXCUTE INSERT PROBLEM");
	  jdbcTemplate.update("INSERT INTO problem(pid, category, description, status, user_id) "
	  		+ "VALUES (?,?,?,?,?)",problemModel.getPId(), problemModel.getCategory(),
	  		problemModel.getDescription(),"Open",problemModel.getUserId());
  }
	
	public String findProblemStatusFromPId(Integer pid) throws SQLException{
		try {
			logger.debug("EXCUTE FIND PROBLEM STATUS FROM PID");
			String sql = "SELECT status FROM problem WHERE pid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{pid}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: NO SUCH PID FOR STATUS");
			return null;
		}
	}
	
	public String findProblemCategoryFromPId(Integer pid) throws SQLException{
		try {
			logger.debug("EXCUTE FIND PROBLEM CATEGORY FROM PID");
			String sql = "SELECT category FROM problem WHERE pid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{pid}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: NO SUCH PID FOR CATEGORY");
			return null;
		}
	}
		
	public void updateProblemStatus(ProblemModel problemModel){
			logger.debug("EXCUTE UPDATE PROBLEM STATUS");
		  jdbcTemplate.update("UPDATE problem SET status = ? WHERE pid = ? ",
				 problemModel.getStatus(),problemModel.getPId());
	
	}
	
	public void deleteProblem(ProblemModel problemModel){
		logger.debug("EXCUTE DELETE PROBLEM");
	  jdbcTemplate.update("DELETE FROM problem WHERE pid = ? ",
			 problemModel.getPId());

}
	
	public List<ProblemModel> findAllProblemFromUserId(Integer userId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ALL PROBLEM FROM USERID");
			String sql = "SELECT * FROM problem WHERE user_id = "+userId;
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        List<ProblemModel> problems = new ArrayList<ProblemModel>();
	        for (Map<String, Object> row : rows) {
	        	ProblemModel problem = new ProblemModel();
	            problem.setpId(Integer.parseInt(String.valueOf(row.get("pid"))));
	            problem.setCategory(String.valueOf(row.get("category")));
	            problem.setDescription(String.valueOf(row.get("description")));
	            problem.setStatus(String.valueOf(row.get("status")));
	            problem.setUserId(Integer.parseInt(String.valueOf(row.get("user_id"))));
	            problems.add(problem);
	        }
	        return problems;
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH USERID");
			return null;
		}
	}
	
	public List<ProblemModel> findAllProblemFromStatus(String status) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ALL PROBLEM FROM STATUS");
			String sql = "SELECT * FROM problem WHERE status = '" + status + "'";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        List<ProblemModel> problems = new ArrayList<ProblemModel>();
	        for (Map<String, Object> row : rows) {
	        	ProblemModel problem = new ProblemModel();
	            problem.setpId(Integer.parseInt(String.valueOf(row.get("pid"))));
	            problem.setCategory(String.valueOf(row.get("category")));
	            problem.setDescription(String.valueOf(row.get("description")));
	            problem.setStatus(String.valueOf(row.get("status")));
	            problem.setUserId(Integer.parseInt(String.valueOf(row.get("user_id"))));
	            problems.add(problem);
	        }
	        return problems;
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH STATUS");
			return null;
		}
	}
	
	public String findProblemDescriptionFromPid(Integer pid) throws SQLException{
		try {
			logger.debug("EXCUTE FIND PROBLEM DESCRIPTION FROM PID");
			String sql = "SELECT description FROM problem WHERE pid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{pid}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH PID");
			return null;
		}
	}
	
	public String findProblemPIdFromUserId(Integer userId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND PROBLEM PID FROM USERID");
			String sql = "SELECT pid FROM problem WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{userId}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH USERID");
			return null;
		}
	}
	
	public Integer findProblemUserIdFromPId(Integer pid) throws SQLException{
		try {
			logger.debug("EXCUTE FIND PROBLEM USERID FROM PID");
			String sql = "SELECT user_id FROM problem WHERE pid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{pid}, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH PID");
			return null;
		}
	}


}