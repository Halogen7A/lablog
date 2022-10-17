package com.halitozgur.lablog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.halitozgur.lablog.model.Personnel;
import com.halitozgur.lablog.repository.PersonnelRepository;

@Controller
public class LoginController {
	

	@GetMapping("/home")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		Personnel personnel = new Personnel();
		model.addAttribute("personnel", personnel);
		return "register";
	}
	
	
	
}
