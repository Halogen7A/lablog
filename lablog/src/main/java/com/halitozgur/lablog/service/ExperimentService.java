package com.halitozgur.lablog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.model.Experiment;
import com.halitozgur.lablog.model.Student;
import com.halitozgur.lablog.repository.ExperimentRepository;
import com.halitozgur.lablog.repository.StudentRepository;

@Service
public class ExperimentService implements ExperimentServiceI{

	@Autowired
	private ExperimentRepository experimentRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<Experiment> getAllExperiments() {
		return experimentRepo.findAll();
	}

	@Override
	public Experiment initializeExperiment(Experiment experiment) {
		return experimentRepo.save(experiment);
	}

	@Override
	public void endExperiment(Long id, Experiment experiment) {
		Optional<Experiment> experimentData = experimentRepo.findById(id);
		Experiment initializedExperiment = experimentData.get();
		if (experimentData.isPresent() && initializedExperiment.getEndDate() == null) {
			initializedExperiment.setEndDate(experiment.getEndDate());
			initializedExperiment.setDamageCost(experiment.getDamageCost());
			experimentRepo.save(initializedExperiment);
		}
	}


	@Override
	public Experiment addStudent(Long studentId, Long experimentId) {
		Experiment experiment = experimentRepo.findById(experimentId).get();
		Student student = studentRepo.findById(studentId).get();
		experiment.getStudents().add(student);
		return experimentRepo.save(experiment);
	}

	

	@Override
	public void deleteExperiment(Long id) {
		experimentRepo.deleteById(id);
		
	}

}
