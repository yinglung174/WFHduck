package com.wfhduck.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wfhduck.app.model.ProblemModel;
@Repository
public interface ProblemRepositoryAPI extends JpaRepository<ProblemModel, Integer> {}