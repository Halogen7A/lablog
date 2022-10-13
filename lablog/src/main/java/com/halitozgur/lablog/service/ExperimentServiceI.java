package com.halitozgur.lablog.service;

import java.util.List;

import com.halitozgur.lablog.model.Experiment;


public interface ExperimentServiceI {

	public List<Experiment> getAllExperiments();
	public Experiment initializeExperiment(Experiment experiment);
	public void endExperiment(Long id, Experiment experiment);
	public Experiment addStudent(Long studentId, Long experimentId);
	public void deleteExperiment(Long id);
}
