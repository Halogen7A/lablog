package com.halitozgur.lablog.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.exception.InconsistentDateException;
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

import lombok.extern.slf4j.Slf4j;

/**
 * Business logic for experiment
 * @author User
 *
 */
@Service
@Slf4j
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
	
	/**
	 * Lists out all experiments.
	 */
	@Override
	public List<Experiment> getAllExperiments() {
		return experimentRepo.findAll();
	}

	/**
	 * Initializes experiment and saves the entity.
	 */
	@Override
	public Experiment initializeExperiment(Experiment experiment) {
		return experimentRepo.save(experiment);
	}

	/**
	 * Ends the experiment by setting end date and damage cost. Checks if the end date is after start date.
	 * @throws InconsistentDateException 
	 */
	@Override
	public Experiment endExperiment(Long id, Experiment experiment) throws InconsistentDateException {
		Optional<Experiment> experimentData = experimentRepo.findById(id);
		Experiment initializedExperiment = experimentData.get();
		String startDate = initializedExperiment.getStartDate();
		String endDate = experiment.getEndDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date start = null;
		Date end = null;
		
		try {
			start = sdf.parse(startDate);
			end = sdf.parse(endDate);
			
		} catch (ParseException e) {
			log.info("End date must be after start date");
			e.printStackTrace();
		}
		
		if (start.compareTo(end) > 0) {
			throw new InconsistentDateException("End date must be after start date");
			
		}
		
		if (experimentData.isPresent() && initializedExperiment.getEndDate() == null) {
			initializedExperiment.setEndDate(experiment.getEndDate());
			initializedExperiment.setDamageCost(experiment.getDamageCost());
		}
		log.info("Dates are consistent!");
		return experimentRepo.save(initializedExperiment);
	}


	/**
	 * Add student to experiment.
	 */
	@Override
	public Experiment addStudent(Long studentId, Long experimentId) {
		Experiment experiment = experimentRepo.findById(experimentId).get();
		Student student = studentRepo.findById(studentId).get();
		experiment.getStudents().add(student);
		return experimentRepo.save(experiment);
	}
	
	
	/**
	 * Add equipment to experiment.
	 */
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

	/**
	 * Sets the one and only supervisor of the experiment.
	 */
	@Override
	public Experiment addSupervisor(Long supervisorId, Long experimentId) {
		Experiment experiment = experimentRepo.findById(experimentId).get();
		Supervisor supervisor = supervisorRepo.findById(supervisorId).get();
		experiment.setSupervisor(supervisor);
		return experimentRepo.save(experiment);
	}

	/**
	 * Deletes experiment.
	 */
	@Override
	public void deleteExperiment(Long id) {
		experimentRepo.deleteById(id);
		
	}

	/**
	 * Finds experiment by its id.
	 */
	@Override
	public Experiment findExperimentById(Long id) {
		return experimentRepo.findById(id).get();
	}

	

}
