package com.halitozgur.lablog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.halitozgur.lablog.model.Equipment;
import com.halitozgur.lablog.repository.EquipmentRepository;
import com.halitozgur.lablog.service.EquipmentService;

/**
 * Finds equipment by name, and contains CRUD operations for equipment.
 * @author User
 *
 */
@Controller
public class EquipmentController {

	@Autowired
	private EquipmentRepository equipmentRepo;
	
	
	@Autowired
	private EquipmentService equipmentService;
	
	/**
	 * For the search bar that finds equipment by name.
	 * @param equipName
	 * @param model
	 * @return
	 */
	@GetMapping("/equipment/search")
	public String findByEquipName(@RequestParam(name="equipName") String equipName, Model model) {
		model.addAttribute("equipment", equipmentRepo.findByEquipName(equipName));
		return "equipment";
	}
	
	/**
	 * Returns a whole list of equipments.
	 * @param model
	 * @return
	 */
	@GetMapping("/equipment")
	public String listEquipments(Model model) {
		model.addAttribute("equipment", equipmentService.getAllEquipments());
		return "equipment";
	}
	
	/**
	 * Adds equipment to the model and forwards the user to "create_equipment.html".
	 * @param model
	 * @return
	 */
	@GetMapping("/equipment/new")
	public String createEquipmentPage(Model model) {
		Equipment equipment = new Equipment();
		model.addAttribute("equipment", equipment);
		return "create_equipment";
	}
	
	/**
	 * Adds the equipment.
	 * @param equipment
	 * @return
	 */
	@PostMapping("/equipment/added")
	public String addEquipment(@ModelAttribute("equipment") Equipment equipment) {
		equipmentService.addEquipment(equipment);
		return "redirect:/equipment";
	}
	
	/**
	 * Adds equipment to the model and forwards the user to "edit_equipment.html".
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/equipment/edit/{id}")
	public String editEquipmentPage(@PathVariable Long id, Model model) {
		model.addAttribute("equipment", equipmentService.getEquipmentById(id));
		return "edit_equipment";
	}
	
	/**
	 * Edits and updates the equipment.
	 * @param id
	 * @param equipment
	 * @return
	 */
	@PostMapping("/equipment/updated/{id}")
	public String updateEquipment(@PathVariable Long id, @ModelAttribute("equipment") Equipment equipment) {
		equipmentService.updateEquipment(id, equipment);
		return "redirect:/equipment";
	}
	
	/**
	 * Deletes the given equipment.
	 * @param id
	 * @return
	 */
	@GetMapping("/equipment/delete/{id}")
	public String deleteEquipment(@PathVariable Long id) {
		equipmentService.deleteEquipmentById(id);
		return "redirect:/equipment";
	}
}
