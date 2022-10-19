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

import lombok.extern.slf4j.Slf4j;

/**
 * Controller for personnel registration.
 * @author User
 *
 */
@Controller
@Slf4j
public class PersonnelController {

	@Autowired
	private PersonnelRepository personnelRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * Checks if another personnel with the same username exists and then, if every validation is satisfied,
	 * creates a newPersonnel (using the previous one as a dto) and sets the attributes.
	 * @param personnel
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/register/addPersonnel")
	public String addPersonnel(@Valid @ModelAttribute("personnel") Personnel personnel, BindingResult result, Model model) {
		Personnel existingPersonnel = personnelRepo.findByPersonnelUserName(personnel.getPersonnelUserName());
		if(existingPersonnel != null && existingPersonnel.getPersonnelUserName() != null && !existingPersonnel.getPersonnelUserName().isEmpty()) {
			result.rejectValue("username", "This username is taken!");
		}
		if(result.hasErrors()) {
			log.info("Invalid Input!");
			model.addAttribute("personnel", personnel);
			return "/register";
		}
		Personnel newPersonnel = new Personnel();
		newPersonnel.setPersonnelPassword(passwordEncoder.encode(personnel.getPersonnelPassword()));
		newPersonnel.setPersonnelUserName(personnel.getPersonnelUserName());
		newPersonnel.setRole(personnel.getRole());
		personnelRepo.save(newPersonnel);
		return "redirect:/login";
	}
}
