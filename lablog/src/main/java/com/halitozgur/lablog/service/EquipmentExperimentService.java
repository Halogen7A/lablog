package com.halitozgur.lablog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.model.Equipment;
import com.halitozgur.lablog.model.EquipmentExperiment;
import com.halitozgur.lablog.model.Experiment;
import com.halitozgur.lablog.repository.EquipmentExperimentRepo;
import com.halitozgur.lablog.repository.EquipmentRepository;
import com.halitozgur.lablog.repository.ExperimentRepository;

@Service
public class EquipmentExperimentService implements EquipmentExperimentServiceI{

	@Autowired
	private EquipmentExperimentRepo eer;
	
	@Autowired
	private ExperimentRepository experimentRepo;
	
	@Autowired
	private EquipmentRepository equipmentRepo;

	@Override
	public void addEquipmentToExperiment(Long equipmentId, Long experimentId, int quantity) {
		Experiment experiment = experimentRepo.findById(experimentId).get();
		Equipment equipment = equipmentRepo.findById(equipmentId).get();
		EquipmentExperiment ee = new EquipmentExperiment();
		
		if (equipment.getAvailableQuantity() - quantity >= 0) {
			ee.setEquipment(equipment);
			ee.setExperiment(experiment);
			ee.setQuantityUsed(quantity);
			eer.save(ee);
		}
		
	}
	
	
}
