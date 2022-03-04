package com.wfhduck.app.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.InvoiceModel;
import com.wfhduck.app.model.InvoiceProblemModel;
import com.wfhduck.app.repository.InvoiceProblemRepository;
import com.wfhduck.app.repository.InvoiceRepository;

@Service
public class InvoiceProblemService {
	
	@Autowired
	InvoiceProblemRepository invoiceProblemRepository;
	
	public List<InvoiceProblemModel> findAllInvoiceProblemFromCustomerId(Integer customerId) throws SQLException{
		return invoiceProblemRepository.findAllInvoiceProblemFromCustomerId(customerId);
	}
	
	public List<InvoiceProblemModel> findAllInvoiceProblemFromTechnicianId(Integer technicianId) throws SQLException{
		return invoiceProblemRepository.findAllInvoiceProblemFromTechnicianId(technicianId);
	}

}