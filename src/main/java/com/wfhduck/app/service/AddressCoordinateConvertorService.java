package com.wfhduck.app.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.AddressCoordinateConvertorModel;
import com.wfhduck.app.model.AddressCoordinateConvertorModel;
import com.wfhduck.app.repository.AddressCoordinateConvertorRepository;
import com.wfhduck.app.repository.AddressCoordinateConvertorRepositoryAPI;

@Service
public class AddressCoordinateConvertorService {
	
	@Autowired
	AddressCoordinateConvertorRepository addresscoordinateconvertorRepository;
	
	@Autowired
	AddressCoordinateConvertorRepositoryAPI addresscoordinateconvertorRepositoryAPI;
	
	public void addAddressCoordinateConvertor(AddressCoordinateConvertorModel addresscoordinateconvertorModel){
		addresscoordinateconvertorRepository.addAddressCoordinateConvertor(addresscoordinateconvertorModel);
  }
	
	public String findAddressCoordinateConvertorAddressFromAddress(String address) throws SQLException{
		return addresscoordinateconvertorRepository.findAddressCoordinateConvertorAddressFromAddress(address);
	}
		
	public void updateAddressCoordinateConvertorCoordinate(AddressCoordinateConvertorModel addresscoordinateconvertorModel){
			addresscoordinateconvertorRepository.updateAddressCoordinateConvertorCoordinate(addresscoordinateconvertorModel);
	
	}
	
	public Double findAddressCoordinateConvertorX(String address) throws SQLException{
		return addresscoordinateconvertorRepository.findAddressCoordinateConvertorX(address);
	}
	
	public Double findAddressCoordinateConvertorY(String address) throws SQLException{
		return addresscoordinateconvertorRepository.findAddressCoordinateConvertorY(address);
	}
	
	
	public String findAddressCoordinateConvertorAddressFromCoordinate(Double x, Double y) throws SQLException{
		return addresscoordinateconvertorRepository.findAddressCoordinateConvertorAddressFromCoordinate(x, y);
	}
	
	public AddressCoordinateConvertorModel createAddressCoordinateConvertor(AddressCoordinateConvertorModel addresscoordinateconvertorModel) throws SQLException{
		return addresscoordinateconvertorRepositoryAPI.save(addresscoordinateconvertorModel);
	}
	public List<AddressCoordinateConvertorModel> getAddressCoordinateConvertors() {
	    return addresscoordinateconvertorRepositoryAPI.findAll();
	}
	public void deleteAddressCoordinateConvertors(Integer accId) {
		addresscoordinateconvertorRepositoryAPI.deleteById(accId);
	}
	public AddressCoordinateConvertorModel updateAddressCoordinateConvertors(Integer accId, AddressCoordinateConvertorModel addresscoordinateconvertorDetails) {
        AddressCoordinateConvertorModel addresscoordinateconvertorModel = addresscoordinateconvertorRepositoryAPI.findById(accId).get();
        addresscoordinateconvertorModel.setAddress(addresscoordinateconvertorDetails.getAddress());
        addresscoordinateconvertorModel.setX(addresscoordinateconvertorDetails.getX());
        addresscoordinateconvertorModel.setY(addresscoordinateconvertorDetails.getY());
        return addresscoordinateconvertorRepositoryAPI.save(addresscoordinateconvertorModel);                                
	}

}