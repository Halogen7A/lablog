package com.halitozgur.lablog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

@Controller
public class EquipmentController {

	@Autowired
	private EquipmentRepository equipmentRepo;
	
	
	@Autowired
	private EquipmentService equipmentService;
	
	@GetMapping("/equipment/search")
	public String findByEquipName(@RequestParam(name="equipName") String equipName, Model model) {
		model.addAttribute("equipment", equipmentRepo.findByEquipName(equipName));
		return "equipment";
	}
	
	@GetMapping("/equipment")
	public String listEquipments(Model model) {
		model.addAttribute("equipment", equipmentService.getAllEquipments());
		return "equipment";
	}
	
	@GetMapping("/equipment/new")
	public String createEquipmentPage(Model model) {
		Equipment equipment = new Equipment();
		model.addAttribute("equipment", equipment);
		return "create_equipment";
	}
	
	@PostMapping("/equipment/added")
	public String addEquipment(@ModelAttribute("equipment") Equipment equipment) {
		equipmentService.addEquipment(equipment);
		return "redirect:/equipment";
	}
	
	@GetMapping("/equipment/edit/{id}")
	public String editEquipmentPage(@PathVariable Long id, Model model) {
		model.addAttribute("equipment", equipmentService.getEquipmentById(id));
		return "edit_equipment";
	}
	
	@PostMapping("/equipment/updated/{id}")
	public String updateEquipment(@PathVariable Long id, @ModelAttribute("equipment") Equipment equipment) {
		equipmentService.updateEquipment(id, equipment);
		return "redirect:/equipment";
	}
	
	@GetMapping("/equipment/delete/{id}")
	public String deleteEquipment(@PathVariable Long id) {
		equipmentService.deleteEquipmentById(id);
		return "redirect:/equipment";
	}
}
