package com.wfhduck.app.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.InvoiceModel;
import com.wfhduck.app.model.ProblemModel;
import com.wfhduck.app.repository.InvoiceRepository;
import com.wfhduck.app.repository.InvoiceRepositoryAPI;

@Service
public class InvoiceService {
	
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	InvoiceRepositoryAPI invoiceRepositoryAPI;
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
	
	public Integer findInoviceServiceFeeFromOId(Integer oId) throws SQLException{
		return invoiceRepository.findInoviceServiceFeeFromOId(oId);
	}
	
	public Double findInoviceDistanceFromOId(Integer oId) throws SQLException{
		return invoiceRepository.findInoviceDistanceFromOId(oId);
	}
	
	public Integer findInoviceCustomerIdFromOId(Integer oId) throws SQLException{
		return invoiceRepository.findInoviceCustomerIdFromOId(oId);
	}
	
	public void deleteInvoice(InvoiceModel invoiceModel){
		invoiceRepository.deleteInvoice(invoiceModel);
	}
	
	public InvoiceModel createInvoice(InvoiceModel invoiceModel) throws SQLException{
		return invoiceRepositoryAPI.save(invoiceModel);
	}
	public List<InvoiceModel> getInvoices() {
	    return invoiceRepositoryAPI.findAll();
	}
	public void deleteInvoices(Integer oId) {
		invoiceRepositoryAPI.deleteById(oId);
	}
	public InvoiceModel updateInvoices(Integer oId, InvoiceModel invoiceDetails) {
        InvoiceModel invoiceModel = invoiceRepositoryAPI.findById(oId).get();
        invoiceModel.setCustomerId(invoiceDetails.getCustomerId());
        invoiceModel.setTechnicianId(invoiceDetails.getTechnicianId());
        invoiceModel.setStatus(invoiceDetails.getStatus());
        invoiceModel.setProblemId(invoiceDetails.getProblemId());
        invoiceModel.setServiceFee(invoiceDetails.getServiceFee());
        invoiceModel.setTransportFee(invoiceDetails.getTransportFee());
        invoiceModel.setDistance(invoiceDetails.getDistance());
        return invoiceRepositoryAPI.save(invoiceModel);                                
	}

}