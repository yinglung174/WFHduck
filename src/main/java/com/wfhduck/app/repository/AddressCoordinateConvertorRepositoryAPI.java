package com.wfhduck.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wfhduck.app.model.AddressCoordinateConvertorModel;
@Repository
public interface AddressCoordinateConvertorRepositoryAPI extends JpaRepository<AddressCoordinateConvertorModel, Integer> {}