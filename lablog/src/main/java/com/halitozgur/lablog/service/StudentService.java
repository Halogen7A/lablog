package com.halitozgur.lablog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.model.Student;
import com.halitozgur.lablog.repository.StudentRepository;

@Service
public class StudentService  implements StudentServiceI{

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public Student addStudent(Student student) {
		return studentRepo.save(student);
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		return studentRepo.findById(id);
	}
	
	
}
