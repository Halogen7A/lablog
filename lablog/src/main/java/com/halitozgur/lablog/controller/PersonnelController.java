package com.halitozgur.lablog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.halitozgur.lablog.model.Personnel;
import com.halitozgur.lablog.repository.PersonnelRepository;

@Controller
public class PersonnelController {

	@Autowired
	private PersonnelRepository personnelRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register/addPersonnel")
	public String addPersonnel(@Valid @ModelAttribute("personnel") Personnel personnel, BindingResult result, Model model) {
		Personnel existingPersonnel = personnelRepo.findByPersonnelUserName(personnel.getPersonnelUserName());
		if(existingPersonnel != null && existingPersonnel.getPersonnelUserName() != null && !existingPersonnel.getPersonnelUserName().isEmpty()) {
			result.rejectValue("username", "This username is taken!");
		}
		if(result.hasErrors()) {
			model.addAttribute("personnel", personnel);
			return "redirect:/register";
		}
		Personnel newPersonnel = new Personnel();
		newPersonnel.setPersonnelPassword(passwordEncoder.encode(personnel.getPersonnelPassword()));
		newPersonnel.setPersonnelUserName(personnel.getPersonnelUserName());
		newPersonnel.setRole(personnel.getRole());
		personnelRepo.save(newPersonnel);
		return "redirect:/login";
	}
}
