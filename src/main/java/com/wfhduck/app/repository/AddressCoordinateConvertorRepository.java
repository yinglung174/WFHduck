package com.wfhduck.app.repository;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.wfhduck.app.model.AddressCoordinateConvertorModel;

@Repository
public class AddressCoordinateConvertorRepository {
	
	private static final Logger logger = LogManager.getLogger(AddressCoordinateConvertorRepository.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addAddressCoordinateConvertor(AddressCoordinateConvertorModel addresscoordinateconvertorModel){
		logger.debug("EXCUTE INSERT ACC");
	  jdbcTemplate.update("INSERT INTO addresscoordinateconvertor(acc_id, address, coordinate_x, coordinate_y) "
	  		+ "VALUES (?,?,?,?)",addresscoordinateconvertorModel.getAccId(), addresscoordinateconvertorModel.getAddress(),
	  		addresscoordinateconvertorModel.getX(),addresscoordinateconvertorModel.getY());
  }
	
	public String findAddressCoordinateConvertorAddressFromAddress(String address) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ACC ADDRESS FROM ADDRESS");
			String sql = "SELECT address FROM addresscoordinateconvertor WHERE address = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{address}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH ADDRESS FOR ADDRESS");
			return null;
		}
	}
		
	public void updateAddressCoordinateConvertorCoordinate(AddressCoordinateConvertorModel addresscoordinateconvertorModel){
			logger.debug("EXCUTE UPDATE ACC Coordinate");
		  jdbcTemplate.update("UPDATE addresscoordinateconvertor SET coordinate_x = ? , coordinate_y = ? WHERE address = ? ",
				 addresscoordinateconvertorModel.getX(),addresscoordinateconvertorModel.getY(),addresscoordinateconvertorModel.getAddress());
	
	}
	
	public Double findAddressCoordinateConvertorX(String address) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ACC X");
			String sql = "SELECT coordinate_x FROM addresscoordinateconvertor WHERE address = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{address}, Double.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH ADDRESS FOR X");
			return null;
		}
	}
	
	public Double findAddressCoordinateConvertorY(String address) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ACC Y");
			String sql = "SELECT coordinate_y FROM addresscoordinateconvertor WHERE address = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{address}, Double.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH ADDRESS FOR Y");
			return null;
		}
	}
	
	
	public String findAddressCoordinateConvertorAddressFromCoordinate(Double x, Double y) throws SQLException{
		try {
			logger.debug("EXCUTE FIND ACC ADDRESS FROM XY");
			String sql = "SELECT address FROM addresscoordinateconvertor WHERE coordinate_x = ? AND coordinate_y = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{x,y}, String.class);
		}catch (EmptyResultDataAccessException e) {
			logger.fatal("FIND NULL: MISMATCH ADDRESS FOR XY");
			return null;
		}
	}


}