package com.halitozgur.lablog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.halitozgur.lablog.model.Experiment;
import com.halitozgur.lablog.repository.StudentRepository;
import com.halitozgur.lablog.repository.SupervisorRepository;
import com.halitozgur.lablog.service.ExperimentService;

@Controller
public class ExperimentController {

	@Autowired
	private ExperimentService experimentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SupervisorRepository supervisorRepo;
	
	 
	@GetMapping("/experiment")
	public String getAllExperiments(Model model) {
		model.addAttribute("experiment", experimentService.getAllExperiments());
		return "experiment";
	}
	
	
	@GetMapping("/experiment/initialize")
	public String createModel(Model model) {
		Experiment experiment = new Experiment();
		model.addAttribute("experiment", experiment);
		return "create_experiment";
	}
	
	@PostMapping("/experiment/initialized")
	public String initializeExperiment(@ModelAttribute("experiment") Experiment experiment) {
		experimentService.initializeExperiment(experiment);
		return "redirect:/experiment";
	}
	
	@GetMapping("/experiment/edit/{experimentId}")
	public String editExperimentPage(@PathVariable Long experimentId, Model model) {
		model.addAttribute("experiment", experimentService.findExperimentById(experimentId));
		return "edit_experiment";
	}
	
	@PostMapping("/experiment/ended/{experimentId}")
	public String endExperiment(@PathVariable Long experimentId, @ModelAttribute("experiment") Experiment experiment) {
		experimentService.endExperiment(experimentId, experiment);
		return "redirect:/experiment";
		
	}
	
	@PostMapping("/experiment/{experimentId}/addStudent")
	public String addStudentToExperiment(@PathVariable Long experimentId, @RequestParam(name="studentName") String studentName, Model model) {
		model.addAttribute("experiment", experimentService.findExperimentById(experimentId));
		experimentService.addStudent(studentRepo.findByStudentName(studentName).getStudentId(), experimentId);
		return "redirect:/experiment";
	}
	
	@PostMapping("/experiment/{experimentId}/addSupervisor")
	public String addSupervisorToExperiment(@PathVariable Long experimentId, @RequestParam(name="supervisorName") String supervisorName, Model model) {
		model.addAttribute("experiment", experimentService.findExperimentById(experimentId));
		experimentService.addSupervisor(supervisorRepo.findBySupervisorName(supervisorName).getSupervisorId(), experimentId);
		return "redirect:/experiment";
	}
	
	
	@PostMapping("/experiment/{experimentId}/addEquipment/")
	public String addEquipmentToExperiment(@PathVariable Long experimentId, @RequestParam(name="equipmentId") Long equipmentId, @RequestParam(name="quantityUsed") int quantityUsed, Model model) {
		model.addAttribute("experiment", experimentService.findExperimentById(experimentId));
		experimentService.addEquipment(equipmentId, experimentId, quantityUsed);
		return "redirect:/experiment";
		
	}
	 
	
	@GetMapping("/experiment/delete/{experimentId}")
	public String deleteExperiment(@PathVariable Long experimentId) {
		experimentService.deleteExperiment(experimentId);
		return "redirect:/experiment";
	}
	
}
