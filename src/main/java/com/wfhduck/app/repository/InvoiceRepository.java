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

@Repository
public class InvoiceRepository {
	
	private static final Logger logger = LogManager.getLogger(InvoiceRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addInvoice(InvoiceModel invoiceModel){
		logger.debug("EXCUTE INSERT INVOICE");
	  jdbcTemplate.update("INSERT INTO invoice(oid, customer_id, technician_id, problem_id, status, transport_fee, service_fee, distance) "
	  		+ "VALUES (?,?,?,?,?,?,?,?)",invoiceModel.getOId(), invoiceModel.getCustomerId(),
	  		invoiceModel.getTechnicianId(),invoiceModel.getProblemId(),"Wait for payment",invoiceModel.getTransportFee(),0,invoiceModel.getDistance());
  }
		
	public void updateInvoiceStatus(InvoiceModel invoiceModel){
			logger.debug("EXCUTE UPDATE INVOICE STATUS");
		  jdbcTemplate.update("UPDATE invoice SET status = ? WHERE oid = ? ",
				 invoiceModel.getStatus(),invoiceModel.getOId());
	}
	
	public void updateInvoiceServiceFee(InvoiceModel invoiceModel){
		logger.debug("EXCUTE UPDATE INVOICE SERVICEFEE");
	  jdbcTemplate.update("UPDATE invoice SET service_fee = ? WHERE oid = ? ",
			 invoiceModel.getServiceFee(),invoiceModel.getOId());
}
	
	public List<InvoiceModel> findAllInvoiceFromCustomerId(Integer customerId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ALL INVOICE FROM CUSTOMERID");
			String sql = "SELECT * FROM invoice WHERE customer_id = "+customerId;
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        List<InvoiceModel> invoices = new ArrayList<InvoiceModel>();
	        for (Map<String, Object> row : rows) {
	        	InvoiceModel invoice = new InvoiceModel();
	            invoice.setOId(Integer.parseInt(String.valueOf(row.get("oid"))));
	            invoice.setCustomerId(Integer.parseInt(String.valueOf(row.get("customer_id"))));
	            invoice.setTechnicianId(Integer.parseInt(String.valueOf(row.get("technician_id"))));
	            invoice.setProblemId(Integer.parseInt(String.valueOf(row.get("problem_id"))));
	            invoice.setStatus(String.valueOf(row.get("status")));
	            invoice.setTransportFee(Integer.parseInt(String.valueOf(row.get("transport_fee"))));
	            invoice.setServiceFee(Integer.parseInt(String.valueOf(row.get("service_fee"))));
	            invoice.setDistance(Double.parseDouble(String.valueOf(row.get("distance"))));
	            invoices.add(invoice);
	        }
	        return invoices;
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH CUSTOMERID");
			return null;
		}
	}
	
	public List<InvoiceModel> findAllInvoiceFromStatusCustomerId(String status, Integer customerId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ALL INVOICE FROM STATUS AND CUSTOMERID");
			String sql = "SELECT * FROM invoice WHERE customer_id = "+customerId+" AND status = '"+status+"'";
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        List<InvoiceModel> invoices = new ArrayList<InvoiceModel>();
	        for (Map<String, Object> row : rows) {
	        	InvoiceModel invoice = new InvoiceModel();
	            invoice.setOId(Integer.parseInt(String.valueOf(row.get("oid"))));
	            invoice.setCustomerId(Integer.parseInt(String.valueOf(row.get("customer_id"))));
	            invoice.setTechnicianId(Integer.parseInt(String.valueOf(row.get("technician_id"))));
	            invoice.setProblemId(Integer.parseInt(String.valueOf(row.get("problem_id"))));
	            invoice.setStatus(String.valueOf(row.get("status")));
	            invoice.setTransportFee(Integer.parseInt(String.valueOf(row.get("transport_fee"))));
	            invoice.setServiceFee(Integer.parseInt(String.valueOf(row.get("service_fee"))));
	            invoice.setDistance(Double.parseDouble(String.valueOf(row.get("distance"))));
	            invoices.add(invoice);
	        }
	        return invoices;
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH STATUS AND CUSTOMERID");
			return null;
		}
	}
	
	public List<InvoiceModel> findAllInvoiceFromTechnicianId(Integer technicianId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ALL INVOICE FROM TECHNICIANID");
			String sql = "SELECT * FROM invoice WHERE technician_id = "+technicianId;
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        List<InvoiceModel> invoices = new ArrayList<InvoiceModel>();
	        for (Map<String, Object> row : rows) {
	        	InvoiceModel invoice = new InvoiceModel();
	            invoice.setOId(Integer.parseInt(String.valueOf(row.get("oid"))));
	            invoice.setCustomerId(Integer.parseInt(String.valueOf(row.get("customer_id"))));
	            invoice.setTechnicianId(Integer.parseInt(String.valueOf(row.get("technician_id"))));
	            invoice.setProblemId(Integer.parseInt(String.valueOf(row.get("problem_id"))));
	            invoice.setStatus(String.valueOf(row.get("status")));
	            invoice.setTransportFee(Integer.parseInt(String.valueOf(row.get("transport_fee"))));
	            invoice.setServiceFee(Integer.parseInt(String.valueOf(row.get("service_fee"))));
	            invoice.setDistance(Double.parseDouble(String.valueOf(row.get("distance"))));
	            invoices.add(invoice);
	        }
	        return invoices;
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH TECHNICIANID");
			return null;
		}
	}
	
	public List<InvoiceModel> findAllInvoiceFromProblemId(Integer problemId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ALL INVOICE FROM PROBLEMID");
			String sql = "SELECT * FROM invoice WHERE problem_id = "+problemId;
			List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        List<InvoiceModel> invoices = new ArrayList<InvoiceModel>();
	        for (Map<String, Object> row : rows) {
	        	InvoiceModel invoice = new InvoiceModel();
	            invoice.setOId(Integer.parseInt(String.valueOf(row.get("oid"))));
	            invoice.setCustomerId(Integer.parseInt(String.valueOf(row.get("customer_id"))));
	            invoice.setTechnicianId(Integer.parseInt(String.valueOf(row.get("technician_id"))));
	            invoice.setProblemId(Integer.parseInt(String.valueOf(row.get("problem_id"))));
	            invoice.setStatus(String.valueOf(row.get("status")));
	            invoice.setTransportFee(Integer.parseInt(String.valueOf(row.get("transport_fee"))));
	            invoice.setServiceFee(Integer.parseInt(String.valueOf(row.get("service_fee"))));
	            invoice.setDistance(Double.parseDouble(String.valueOf(row.get("distance"))));
	            invoices.add(invoice);
	        }
	        return invoices;
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH PROBLEMID");
			return null;
		}
	}
	
	public Integer findInoviceProblemIdFromOId(Integer oId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND INVOICE PROBLEMID FROM OID");
			String sql = "SELECT problem_id FROM invoice WHERE oid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{oId}, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH OID");
			return null;
		}
	}
	
	public Integer findInoviceTechnicianIdFromOId(Integer oId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND INVOICE TECHNICIAN FROM OID");
			String sql = "SELECT technician_id FROM invoice WHERE oid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{oId}, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH OID");
			return null;
		}
	}
	
	public Integer findInoviceCustomerIdFromOId(Integer oId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND INVOICE CUSTOMERID FROM OID");
			String sql = "SELECT customer_id FROM invoice WHERE oid = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{oId}, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH OID");
			return null;
		}
	}
	
	public Integer findInoviceOIdFromProblemId(Integer problemId) throws SQLException{
		try {
			logger.debug("EXCUTE FIND INVOICE OID FROM PROBLEMID");
			String sql = "SELECT oid FROM invoice WHERE problem_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{problemId}, Integer.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH OID");
			return null;
		}
	}
		
		public Double findInoviceDistanceFromOId(Integer oId) throws SQLException{
			try {
				logger.debug("EXCUTE FIND INVOICE DISTANCE FROM OID");
				String sql = "SELECT distance FROM invoice WHERE oid = ?";
			return jdbcTemplate.queryForObject(sql, new Object[]{oId}, Double.class);
			}catch (EmptyResultDataAccessException e) {
				logger.fatal("FIND NULL: MISMATCH OID");
				return null;
			}
		}
		
		public Integer findInoviceTransportFeeFromOId(Integer oId) throws SQLException{
			try {
				logger.debug("EXCUTE FIND INVOICE TRANSPORTFEE FROM OID");
				String sql = "SELECT transport_fee FROM invoice WHERE oid = ?";
			return jdbcTemplate.queryForObject(sql, new Object[]{oId}, Integer.class);
			}catch (EmptyResultDataAccessException e) {
				logger.fatal("FIND NULL: MISMATCH OID");
				return null;
			}
		}
		
		public Integer findInoviceServiceFeeFromOId(Integer oId) throws SQLException{
			try {
				logger.debug("EXCUTE FIND INVOICE SERVICEFEE FROM OID");
				String sql = "SELECT service_fee FROM invoice WHERE oid = ?";
			return jdbcTemplate.queryForObject(sql, new Object[]{oId}, Integer.class);
			}catch (EmptyResultDataAccessException e) {
				logger.fatal("FIND NULL: MISMATCH OID");
				return null;
			}
		}

}