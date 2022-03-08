package com.wfhduck.app.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wfhduck.app.model.AddressCoordinateConvertorModel;
import com.wfhduck.app.model.CustomerModel;
import com.wfhduck.app.model.InvoiceModel;
import com.wfhduck.app.model.ProblemModel;
import com.wfhduck.app.service.AddressCoordinateConvertorService;
import com.wfhduck.app.service.CustomerService;
import com.wfhduck.app.service.InvoiceService;
import com.wfhduck.app.service.ProblemService;


@RestController
@RequestMapping("/api")
public class APIController {
	
	@Autowired
    private CustomerService customerService;
	
	@Autowired
    private ProblemService problemService;
	
	@Autowired
    private AddressCoordinateConvertorService addressCoordinateConvertorService;
	
	@Autowired
    private InvoiceService invoiceService;
        
	@RequestMapping(value="/customer", method=RequestMethod.POST)
	public CustomerModel createCustomer(@RequestBody CustomerModel customer) throws SQLException {
	    return customerService.createCustomer(customer);
	}
	@RequestMapping(value="/customer", method=RequestMethod.GET)
	public List<CustomerModel> readCustomers() {
	    return customerService.getCustomers();
	}
	@RequestMapping(value="/customer/{user_id}", method=RequestMethod.DELETE)
	public void deleteCustomers(@PathVariable(value = "user_id") Integer id) {
	    customerService.deleteCustomers(id);
	}
	@RequestMapping(value="/customer/{user_id}", method=RequestMethod.PUT)
	public CustomerModel updateCustomers(@PathVariable(value = "user_id") Integer id, @RequestBody CustomerModel customerDetails) {
	    return customerService.updateCustomers(id, customerDetails);
	}
	
	@RequestMapping(value="/problem", method=RequestMethod.POST)
	public ProblemModel createProblem(@RequestBody ProblemModel problem) throws SQLException {
	    return problemService.createProblem(problem);
	}
	@RequestMapping(value="/problem", method=RequestMethod.GET)
	public List<ProblemModel> readProblems() {
	    return problemService.getProblems();
	}
	@RequestMapping(value="/problem/{pid}", method=RequestMethod.DELETE)
	public void deleteProblems(@PathVariable(value = "pid") Integer id) {
	    problemService.deleteProblems(id);
	}
	@RequestMapping(value="/problem/{pid}", method=RequestMethod.PUT)
	public ProblemModel updateProblems(@PathVariable(value = "pid") Integer id, @RequestBody ProblemModel problemDetails) {
	    return problemService.updateProblems(id, problemDetails);
	}
	
	@RequestMapping(value="/addressCoordinateConvertor", method=RequestMethod.POST)
	public AddressCoordinateConvertorModel createAddressCoordinateConvertor(@RequestBody AddressCoordinateConvertorModel addressCoordinateConvertor) throws SQLException {
	    return addressCoordinateConvertorService.createAddressCoordinateConvertor(addressCoordinateConvertor);
	}
	@RequestMapping(value="/addressCoordinateConvertor", method=RequestMethod.GET)
	public List<AddressCoordinateConvertorModel> readAddressCoordinateConvertors() {
	    return addressCoordinateConvertorService.getAddressCoordinateConvertors();
	}
	@RequestMapping(value="/addressCoordinateConvertor/{acc_id}", method=RequestMethod.DELETE)
	public void deleteAddressCoordinateConvertors(@PathVariable(value = "acc_id") Integer id) {
	    addressCoordinateConvertorService.deleteAddressCoordinateConvertors(id);
	}
	@RequestMapping(value="/addressCoordinateConvertor/{acc_id}", method=RequestMethod.PUT)
	public AddressCoordinateConvertorModel updateAddressCoordinateConvertors(@PathVariable(value = "acc_id") Integer id, @RequestBody AddressCoordinateConvertorModel addressCoordinateConvertorDetails) {
	    return addressCoordinateConvertorService.updateAddressCoordinateConvertors(id, addressCoordinateConvertorDetails);
	}
	
	@RequestMapping(value="/invoice", method=RequestMethod.POST)
	public InvoiceModel createInvoice(@RequestBody InvoiceModel invoice) throws SQLException {
	    return invoiceService.createInvoice(invoice);
	}
	@RequestMapping(value="/invoice", method=RequestMethod.GET)
	public List<InvoiceModel> readInvoices() {
	    return invoiceService.getInvoices();
	}
	@RequestMapping(value="/invoice/{oid}", method=RequestMethod.DELETE)
	public void deleteInvoices(@PathVariable(value = "oid") Integer id) {
	    invoiceService.deleteInvoices(id);
	}
	@RequestMapping(value="/invoice/{oid}", method=RequestMethod.PUT)
	public InvoiceModel updateInvoices(@PathVariable(value = "oid") Integer id, @RequestBody InvoiceModel invoiceDetails) {
	    return invoiceService.updateInvoices(id, invoiceDetails);
	}
}