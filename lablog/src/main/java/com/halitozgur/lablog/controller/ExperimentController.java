package com.halitozgur.lablog.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.halitozgur.lablog.exception.InconsistentDateException;
import com.halitozgur.lablog.model.Experiment;
import com.halitozgur.lablog.repository.StudentRepository;
import com.halitozgur.lablog.repository.SupervisorRepository;
import com.halitozgur.lablog.service.ExperimentService;


/**
 * CRUD operations on experiment, adds student, equipment, and supervisor.
 * @author User
 *
 */
@Controller
public class ExperimentController {

	@Autowired
	private ExperimentService experimentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SupervisorRepository supervisorRepo;
	
	 
	/**
	 * Lists out all experiments.
	 * @param model
	 * @return
	 */
	@GetMapping("/experiment")
	public String getAllExperiments(Model model) {
		model.addAttribute("experiment", experimentService.getAllExperiments());
		return "experiment";
	}
	
	
	/**
	 * Adds experiment to model.
	 * @param model
	 * @return
	 */
	@GetMapping("/experiment/initialize")
	public String createModel(Model model) {
		Experiment experiment = new Experiment();
		model.addAttribute("experiment", experiment);
		return "create_experiment";
	}
	
	/**
	 * Adds experiment to experiment table.
	 * @param experiment
	 * @return
	 */
	@PostMapping("/experiment/initialized")
	public String initializeExperiment(@ModelAttribute("experiment") Experiment experiment) {
		experimentService.initializeExperiment(experiment);
		return "redirect:/experiment";
	}
	
	/**
	 * Grabs the experiment to be edited and sends the user to "edit_experiment.html".
	 * @param experimentId
	 * @param model
	 * @return
	 */
	@GetMapping("/experiment/edit/{experimentId}")
	public String editExperimentPage(@PathVariable Long experimentId, Model model) {
		model.addAttribute("experiment", experimentService.findExperimentById(experimentId));
		return "edit_experiment";
	}
	
	/**
	 * Ends the experiment by specifying the end date and damage cost. Checks if the end date is after start date.
	 * @param experimentId
	 * @param experiment
	 * @return
	 */
	@PostMapping("/experiment/ended/{experimentId}")
	public String endExperiment(@Valid @PathVariable Long experimentId, @ModelAttribute("experiment") Experiment experiment) {
		try {
			experimentService.endExperiment(experimentId, experiment);
		} catch (InconsistentDateException e) {
			e.printStackTrace();
		}
		return "redirect:/experiment";
		
	}
	
	/**
	 * Adds student to experiment
	 * @param experimentId
	 * @param studentName
	 * @param model
	 * @return
	 */
	@PostMapping("/experiment/{experimentId}/addStudent")
	public String addStudentToExperiment(@PathVariable Long experimentId, @RequestParam(name="studentName") String studentName, Model model) {
		model.addAttribute("experiment", experimentService.findExperimentById(experimentId));
		experimentService.addStudent(studentRepo.findByStudentName(studentName).getStudentId(), experimentId);
		return "redirect:/experiment";
	}
	
	/**
	 * Sets experiment's one and only supervisor.
	 * @param experimentId
	 * @param supervisorName
	 * @param model
	 * @return
	 */
	@PostMapping("/experiment/{experimentId}/addSupervisor")
	public String addSupervisorToExperiment(@PathVariable Long experimentId, @RequestParam(name="supervisorName") String supervisorName, Model model) {
		model.addAttribute("experiment", experimentService.findExperimentById(experimentId));
		experimentService.addSupervisor(supervisorRepo.findBySupervisorName(supervisorName).getSupervisorId(), experimentId);
		return "redirect:/experiment";
	}
	
	
	/**
	 * Adds the equipment to experiment along with the quantity used.
	 * @param experimentId
	 * @param equipmentId
	 * @param quantityUsed
	 * @param model
	 * @return
	 */
	@PostMapping("/experiment/{experimentId}/addEquipment/")
	public String addEquipmentToExperiment(@PathVariable Long experimentId, @RequestParam(name="equipmentId") Long equipmentId, @RequestParam(name="quantityUsed") int quantityUsed, Model model) {
		model.addAttribute("experiment", experimentService.findExperimentById(experimentId));
		experimentService.addEquipment(equipmentId, experimentId, quantityUsed);
		return "redirect:/experiment";
		
	}
	 
	
	/**
	 * Deletes experiment.
	 * @param experimentId
	 * @return
	 */
	@GetMapping("/experiment/delete/{experimentId}")
	public String deleteExperiment(@PathVariable Long experimentId) {
		experimentService.deleteExperiment(experimentId);
		return "redirect:/experiment";
	}
	
}
