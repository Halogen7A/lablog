package com.halitozgur.lablog.servicemethod;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.halitozgur.lablog.model.Equipment;
import com.halitozgur.lablog.model.Experiment;
import com.halitozgur.lablog.model.Student;
import com.halitozgur.lablog.model.Task;
import com.halitozgur.lablog.model.Type;
import com.halitozgur.lablog.repository.EquipmentRepository;
import com.halitozgur.lablog.repository.StudentRepository;
import com.halitozgur.lablog.repository.TaskRepository;
import com.halitozgur.lablog.service.EquipmentService;
import com.halitozgur.lablog.service.ExperimentService;
import com.halitozgur.lablog.service.PersonnelDetailsService;
import com.halitozgur.lablog.service.StudentService;
import com.halitozgur.lablog.service.TaskService;

@SpringBootTest
public class ServiceMethodTest {

	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private EquipmentRepository equipmentRepo;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private ExperimentService experimentService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private PersonnelDetailsService personnelService;
	
	@ParameterizedTest
	@ValueSource(strings = {"Non-existent Username", "Another Non-existent Username"})
	void personnelUsernameException(String input) {
		Assertions.assertThrows(UsernameNotFoundException.class, 
				() -> personnelService.loadUserByUsername(input));
	}
	
	@Test
	void studentAdd() {
		
		Student student = Student.builder()
				.birthDate("1940-09-12").studentName("Random Name").studentEmail("someemail@mail.com").build();
		
		studentService.addStudent(student);
		Assertions.assertTrue(studentRepo.findById(student.getStudentId()).isPresent());
		studentRepo.delete(student);
		
	}
	
	@Test
	void experimentList() {
		List<Experiment> experiment = experimentService.getAllExperiments();
		Assertions.assertTrue(experiment.size() > 0);
	}
	
	@Test
	void addDeleteTask() {
		Task task = Task.builder()
				.description("some description")
				.dueDate("1970-02-02")
				.taskType(Type.CHARGE)
				.build();
		taskService.addTask(task);
		Assertions.assertTrue(taskRepo.findById(task.getTaskId()).isPresent());
		taskService.deleteTaskById(task.getTaskId());
		Assertions.assertFalse(taskRepo.findById(task.getTaskId()).isPresent());
	}
	
	@Test
	void equipmentUpdate() {
		Equipment equipment = Equipment.builder()
										.equipName("Test Equipment")
										.availableQuantity(10)
										.unitPrice(1.99)
										.build();
		equipmentRepo.save(equipment);
		
		Equipment newEquipment = Equipment.builder()
										  .equipName("Test Equipment")
										  .availableQuantity(15)
										  .unitPrice(1.99)
										  .build();
		
		equipmentService.updateEquipment(equipment.getEquipmentId(), newEquipment);
		Assertions.assertTrue(equipmentRepo.findByEquipName("Test Equipment").getAvailableQuantity() == 15);
		equipmentRepo.delete(equipment);
	}
}
