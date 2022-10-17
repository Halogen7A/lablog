package com.halitozgur.lablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.model.Equipment;
import com.halitozgur.lablog.repository.EquipmentRepository;

@Service
public class EquipmentService implements EquipmentServiceI{

	@Autowired
	private EquipmentRepository equipmentRepo;

	@Override
	public List<Equipment> getAllEquipments() {
		return equipmentRepo.findAll();
	}

	@Override
	public Equipment addEquipment(Equipment equipment) {
		return equipmentRepo.save(equipment);
	}

	@Override
	public Equipment updateEquipment(Long id, Equipment equipment) {
		Equipment existingEquipment = equipmentRepo.findById(id).get();
		existingEquipment.setAvailableQuantity(equipment.getAvailableQuantity());
		existingEquipment.setEquipName(equipment.getEquipName());
		existingEquipment.setUnitPrice(equipment.getUnitPrice());
		return equipmentRepo.save(existingEquipment);
	}

	@Override
	public void deleteEquipmentById(Long id) {
		equipmentRepo.deleteById(id);
		
	}

	@Override
	public Equipment getEquipmentById(Long id) {
		return equipmentRepo.findById(id).get();
	}
}
