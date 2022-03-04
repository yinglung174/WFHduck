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

import com.wfhduck.app.model.InvoiceModel;
import com.wfhduck.app.model.InvoiceProblemModel;

@Repository
public class InvoiceProblemRepository {
	
	private static final Logger logger = LogManager.getLogger(InvoiceProblemRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<InvoiceProblemModel> findAllInvoiceProblemFromCustomerId(Integer customerId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ALL INVOICE & PROBLEM FROM CUSTOMERID");
			String sql = "SELECT * FROM invoice i, problem p WHERE i.problem_id = p.pid AND customer_id = "+customerId;
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        List<InvoiceProblemModel> invoices = new ArrayList<InvoiceProblemModel>();
	        for (Map<String, Object> row : rows) {
	        	InvoiceProblemModel invoice = new InvoiceProblemModel();
	            invoice.setOId(Integer.parseInt(String.valueOf(row.get("oid"))));
	            invoice.setCustomerId(Integer.parseInt(String.valueOf(row.get("customer_id"))));
	            invoice.setTechnicianId(Integer.parseInt(String.valueOf(row.get("technician_id"))));
	            invoice.setProblemId(Integer.parseInt(String.valueOf(row.get("problem_id"))));
	            invoice.setStatus(String.valueOf(row.get("status")));
	            invoice.setTransportFee(Integer.parseInt(String.valueOf(row.get("transport_fee"))));
	            invoice.setServiceFee(Integer.parseInt(String.valueOf(row.get("service_fee"))));
	            invoice.setDistance(Double.parseDouble(String.valueOf(row.get("distance"))));
	            invoice.setCategory(String.valueOf(row.get("category")));
	            invoice.setDescription(String.valueOf(row.get("description")));
	            invoice.setPStatus(String.valueOf(row.get("status_1")));
	            invoices.add(invoice);
	        }
	        return invoices;
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH CUSTOMERID");
			return null;
		}
	}
	
	public List<InvoiceProblemModel> findAllInvoiceProblemFromTechnicianId(Integer technicianId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ALL INVOICE & PROBLEM FROM TECHNICIANID");
			String sql = "SELECT * FROM invoice i, problem p WHERE i.problem_id = p.pid AND technician_id = "+technicianId;
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        List<InvoiceProblemModel> invoices = new ArrayList<InvoiceProblemModel>();
	        for (Map<String, Object> row : rows) {
	        	InvoiceProblemModel invoice = new InvoiceProblemModel();
	            invoice.setOId(Integer.parseInt(String.valueOf(row.get("oid"))));
	            invoice.setCustomerId(Integer.parseInt(String.valueOf(row.get("customer_id"))));
	            invoice.setTechnicianId(Integer.parseInt(String.valueOf(row.get("technician_id"))));
	            invoice.setProblemId(Integer.parseInt(String.valueOf(row.get("problem_id"))));
	            invoice.setStatus(String.valueOf(row.get("status")));
	            invoice.setTransportFee(Integer.parseInt(String.valueOf(row.get("transport_fee"))));
	            invoice.setServiceFee(Integer.parseInt(String.valueOf(row.get("service_fee"))));
	            invoice.setDistance(Double.parseDouble(String.valueOf(row.get("distance"))));
	            invoice.setCategory(String.valueOf(row.get("category")));
	            invoice.setDescription(String.valueOf(row.get("description")));
	            invoice.setPStatus(String.valueOf(row.get("status_1")));
	            invoices.add(invoice);
	        }
	        return invoices;
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH TECHNICIANID");
			return null;
		}
	}

}