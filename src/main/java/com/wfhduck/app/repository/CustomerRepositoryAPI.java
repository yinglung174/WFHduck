package com.wfhduck.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wfhduck.app.model.CustomerModel;
@Repository
public interface CustomerRepositoryAPI extends JpaRepository<CustomerModel, Integer> {}