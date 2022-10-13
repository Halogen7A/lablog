package com.halitozgur.lablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.halitozgur.lablog.model.Experiment;

@Repository
public interface ExperimentRepository extends JpaRepository<Experiment, Long>{

}
