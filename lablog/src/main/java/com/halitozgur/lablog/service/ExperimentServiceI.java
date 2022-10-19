package com.halitozgur.lablog.service;


import java.util.List;

import com.halitozgur.lablog.exception.InconsistentDateException;
import com.halitozgur.lablog.model.EquipmentOrder;
import com.halitozgur.lablog.model.Experiment;


public interface ExperimentServiceI {

	public List<Experiment> getAllExperiments();
	public Experiment findExperimentById(Long id);
	public Experiment initializeExperiment(Experiment experiment);
	public Experiment endExperiment(Long id, Experiment experiment) throws InconsistentDateException;
	public Experiment addStudent(Long studentId, Long experimentId);
	public EquipmentOrder addEquipment(Long studentId, Long experimentId, int quantity);
	public Experiment addSupervisor(Long supervisorId, Long experimentId);
	public void deleteExperiment(Long id);
}
