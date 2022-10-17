package com.halitozgur.lablog.service;

import java.util.List;

import com.halitozgur.lablog.model.Equipment;

public interface EquipmentServiceI {

	public List<Equipment> getAllEquipments();
	public Equipment getEquipmentById(Long id);
	public Equipment addEquipment(Equipment equipment);
	public Equipment updateEquipment(Long id, Equipment equipment);
	public void deleteEquipmentById(Long id);
}
