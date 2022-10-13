package com.halitozgur.lablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.halitozgur.lablog.model.EquipmentExperiment;
import com.halitozgur.lablog.model.EquipmentExperimentKey;

@Repository
public interface EquipmentExperimentRepo extends JpaRepository<EquipmentExperiment, EquipmentExperimentKey>{

}
