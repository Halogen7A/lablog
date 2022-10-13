package com.halitozgur.lablog.service;

import java.util.List;
import java.util.Optional;

import com.halitozgur.lablog.model.Student;

public interface StudentServiceI {
	
	public List<Student> getAllStudents();
	public Student addStudent(Student student);
	public Optional<Student> getStudentById(Long id);

}
