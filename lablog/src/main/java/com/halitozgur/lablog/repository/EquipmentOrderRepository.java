package com.halitozgur.lablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.halitozgur.lablog.model.EquipmentOrder;

@Repository
public interface EquipmentOrderRepository extends JpaRepository<EquipmentOrder, Long>{

}
