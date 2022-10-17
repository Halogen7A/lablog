package com.halitozgur.lablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.halitozgur.lablog.model.Equipment;


public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
	
	public Equipment findByEquipName(String equipName);
}
