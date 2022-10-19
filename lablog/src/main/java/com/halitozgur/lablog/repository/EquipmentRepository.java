package com.halitozgur.lablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.halitozgur.lablog.model.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>{
	
	public Equipment findByEquipName(String equipName);
}
