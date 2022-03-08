package com.wfhduck.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wfhduck.app.model.InvoiceModel;
@Repository
public interface InvoiceRepositoryAPI extends JpaRepository<InvoiceModel, Integer> {}