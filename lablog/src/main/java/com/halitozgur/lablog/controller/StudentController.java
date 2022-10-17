package com.halitozgur.lablog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.halitozgur.lablog.model.Student;
import com.halitozgur.lablog.repository.StudentRepository;
import com.halitozgur.lablog.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@GetMapping("/getStudentById/{id}")
	public Optional<Student> getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}
	
	@GetMapping("/getStudentByName/{studentName}")
	public Student getStudentByName(@PathVariable String studentName) {
		return studentRepo.findByStudentName(studentName);
	}

	@PostMapping("/add")
	public String addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
		return "new student added!";
	}
}
