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

/**
 * Gets all available students, adds students, gets students by id and name.
 * @author User
 *
 */
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	/**
	 * returns a list of students available in the student table.
	 * @return
	 */
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	/**
	 * Returns the student that has the id passed in as an argument.
	 * @param id
	 * @return
	 */
	@GetMapping("/getStudentById/{id}")
	public Optional<Student> getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}
	
	/**
	 * Returns the student that has the name passed in as an argument.
	 * @param studentName
	 * @return
	 */
	@GetMapping("/getStudentByName/{studentName}")
	public Student getStudentByName(@PathVariable String studentName) {
		return studentRepo.findByStudentName(studentName);
	}

	/**
	 * Adds student to the student table.
	 * @param student
	 * @return
	 */
	@PostMapping("/add")
	public String addStudent(@RequestBody Student student) {
		studentService.addStudent(student);
		return "new student added!";
	}
}
