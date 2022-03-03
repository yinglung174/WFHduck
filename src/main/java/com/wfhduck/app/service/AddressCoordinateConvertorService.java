package com.wfhduck.app.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wfhduck.app.model.AddressCoordinateConvertorModel;
import com.wfhduck.app.repository.AddressCoordinateConvertorRepository;

@Service
public class AddressCoordinateConvertorService {
	
	@Autowired
	AddressCoordinateConvertorRepository addresscoordinateconvertorRepository;
	
	public void addAddressCoordinateConvertor(AddressCoordinateConvertorModel addresscoordinateconvertorModel){
		addresscoordinateconvertorRepository.addAddressCoordinateConvertor(addresscoordinateconvertorModel);
  }
	
	public String findAddressCoordinateConvertorAddressFromAddress(String address) throws SQLException{
		return addresscoordinateconvertorRepository.findAddressCoordinateConvertorAddressFromAddress(address);
	}
		
	public void updateAddressCoordinateConvertorCoordinate(AddressCoordinateConvertorModel addresscoordinateconvertorModel){
			addresscoordinateconvertorRepository.updateAddressCoordinateConvertorCoordinate(addresscoordinateconvertorModel);
	
	}
	
	public String findAddressCoordinateConvertorX(String address) throws SQLException{
		return addresscoordinateconvertorRepository.findAddressCoordinateConvertorX(address);
	}
	
	public String findAddressCoordinateConvertorY(String address) throws SQLException{
		return addresscoordinateconvertorRepository.findAddressCoordinateConvertorY(address);
	}
	
	
	public String findAddressCoordinateConvertorAddressFromCoordinate(Double x, Double y) throws SQLException{
		return addresscoordinateconvertorRepository.findAddressCoordinateConvertorAddressFromCoordinate(x, y);
	}

}