package com.wfhduck.app.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.InvoiceModel;
import com.wfhduck.app.repository.InvoiceRepository;

@Service
public class InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepository;
	public void addInvoice(InvoiceModel invoiceModel){
		invoiceRepository.addInvoice(invoiceModel);
	}
		
	public void updateInvoiceStatus(InvoiceModel invoiceModel){
		invoiceRepository.updateInvoiceStatus(invoiceModel);
	}
	
	public void updateInvoiceServiceFee(InvoiceModel invoiceModel){
		invoiceRepository.updateInvoiceServiceFee(invoiceModel);
	}
	
	public List<InvoiceModel> findAllInvoiceFromCustomerId(Integer customerId) throws SQLException{
		return invoiceRepository.findAllInvoiceFromCustomerId(customerId);
	}
	
	public List<InvoiceModel> findAllInvoiceFromStatusCustomerId(String status, Integer customerId) throws SQLException{
		return invoiceRepository.findAllInvoiceFromStatusCustomerId(status, customerId);
	}
	
	public List<InvoiceModel> findAllInvoiceFromTechnicianId(Integer technicianId) throws SQLException{
		return invoiceRepository.findAllInvoiceFromTechnicianId(technicianId);
	}
	
	public List<InvoiceModel> findAllInvoiceFromProblemId(Integer problemId) throws SQLException{
		return invoiceRepository.findAllInvoiceFromProblemId(problemId);
	}
	
	public Integer findInoviceProblemIdFromOId(Integer oId) throws SQLException{
		return invoiceRepository.findInoviceProblemIdFromOId(oId);
	}
	
	public Integer findInoviceOIdFromProblemId(Integer problemId) throws SQLException{
		return invoiceRepository.findInoviceOIdFromProblemId(problemId);
	}
	
	public Integer findInoviceTechnicianIdFromOId(Integer oId) throws SQLException{
		return invoiceRepository.findInoviceTechnicianIdFromOId(oId);
	}
	
	public Integer findInoviceTransportFeeFromOId(Integer oId) throws SQLException{
		return invoiceRepository.findInoviceTransportFeeFromOId(oId);
	}
	
	public Double findInoviceDistanceFromOId(Integer oId) throws SQLException{
		return invoiceRepository.findInoviceDistanceFromOId(oId);
	}
	
	public Integer findInoviceCustomerIdFromOId(Integer oId) throws SQLException{
		return invoiceRepository.findInoviceCustomerIdFromOId(oId);
	}

}