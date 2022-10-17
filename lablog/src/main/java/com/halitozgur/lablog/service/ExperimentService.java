package com.halitozgur.lablog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.model.Equipment;
import com.halitozgur.lablog.model.EquipmentOrder;
import com.halitozgur.lablog.model.Experiment;
import com.halitozgur.lablog.model.Student;
import com.halitozgur.lablog.model.Supervisor;
import com.halitozgur.lablog.repository.EquipmentOrderRepository;
import com.halitozgur.lablog.repository.EquipmentRepository;
import com.halitozgur.lablog.repository.ExperimentRepository;
import com.halitozgur.lablog.repository.StudentRepository;
import com.halitozgur.lablog.repository.SupervisorRepository;

@Service
public class ExperimentService implements ExperimentServiceI{

	@Autowired
	private ExperimentRepository experimentRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SupervisorRepository supervisorRepo;
	
	@Autowired
	private EquipmentRepository equipmentRepo;
	
	@Autowired
	private EquipmentOrderRepository orderRepo;
	
	@Override
	public List<Experiment> getAllExperiments() {
		return experimentRepo.findAll();
	}

	@Override
	public Experiment initializeExperiment(Experiment experiment) {
		return experimentRepo.save(experiment);
	}

	@Override
	public Experiment endExperiment(Long id, Experiment experiment) {
		Optional<Experiment> experimentData = experimentRepo.findById(id);
		Experiment initializedExperiment = experimentData.get();
		if (experimentData.isPresent() && initializedExperiment.getEndDate() == null) {
			initializedExperiment.setEndDate(experiment.getEndDate());
			initializedExperiment.setDamageCost(experiment.getDamageCost());
		}
		return experimentRepo.save(initializedExperiment);
	}


	@Override
	public Experiment addStudent(Long studentId, Long experimentId) {
		Experiment experiment = experimentRepo.findById(experimentId).get();
		Student student = studentRepo.findById(studentId).get();
		experiment.getStudents().add(student);
		return experimentRepo.save(experiment);
	}
	
	
	@Override
	public EquipmentOrder addEquipment(Long equipmentId, Long experimentId, int quantity) {
		Experiment experiment = experimentRepo.findById(experimentId).get();
		Equipment equipment = equipmentRepo.findById(equipmentId).get();
		EquipmentOrder order = new EquipmentOrder();
		order.setEquipment(equipment);
		order.setExperiment(experiment);
		order.setQuantityUsed(quantity);
		return orderRepo.save(order);
	}

	@Override
	public Experiment addSupervisor(Long supervisorId, Long experimentId) {
		Experiment experiment = experimentRepo.findById(experimentId).get();
		Supervisor supervisor = supervisorRepo.findById(supervisorId).get();
		experiment.setSupervisor(supervisor);
		return experimentRepo.save(experiment);
	}

	@Override
	public void deleteExperiment(Long id) {
		experimentRepo.deleteById(id);
		
	}

	@Override
	public Experiment findExperimentById(Long id) {
		return experimentRepo.findById(id).get();
	}

	

}
