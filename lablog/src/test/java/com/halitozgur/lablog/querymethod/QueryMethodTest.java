package com.halitozgur.lablog.querymethod;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.halitozgur.lablog.model.Equipment;
import com.halitozgur.lablog.model.Personnel;
import com.halitozgur.lablog.model.Student;
import com.halitozgur.lablog.model.Supervisor;
import com.halitozgur.lablog.model.Task;
import com.halitozgur.lablog.model.Type;
import com.halitozgur.lablog.repository.EquipmentRepository;
import com.halitozgur.lablog.repository.PersonnelRepository;
import com.halitozgur.lablog.repository.StudentRepository;
import com.halitozgur.lablog.repository.SupervisorRepository;
import com.halitozgur.lablog.repository.TaskRepository;


@SpringBootTest
public class QueryMethodTest {

	@Autowired
	private EquipmentRepository equipmentRepo;
	
	@Autowired 
	private PersonnelRepository personnelRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SupervisorRepository supervisorRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Test
	void equipmentName() {
		String name = "Test Equipment";
		Equipment equipment = Equipment.builder()
										.equipName(name)
										.availableQuantity(10)
										.unitPrice(1.99)
										.build();
		equipmentRepo.save(equipment);
		Assertions.assertThat(equipmentRepo.findByEquipName(name).getEquipName()).isEqualTo(name);
		equipmentRepo.delete(equipment);
	}
	
	@Test
	void personnelName() {
		String name = "Test Personnel";
		Personnel personnel = Personnel.builder()
										.personnelUserName(name)
										.personnelPassword("testpassword2")
										.role("PERSONNEL")
										.build();
				
		personnelRepo.save(personnel);
		Assertions.assertThat(personnelRepo.findByPersonnelUserName(name).getPersonnelUserName()).isEqualTo(name);
		personnelRepo.delete(personnel);
	}
	
	@Test
	void studentName() {
		String name = "Test Student";
		Student student = Student.builder()
								 .studentName(name)
								 .studentEmail("test@gmail.com")
								 .birthDate("2002-06-26")
								 .build();
		studentRepo.save(student);
		Assertions.assertThat(studentRepo.findByStudentName(name).getStudentName()).isEqualTo(name);
		studentRepo.delete(student);
	}
	
	@Test
	void supervisorName() {
		String name = "Test Supervisor";
		Supervisor supervisor = Supervisor.builder()
										  .supervisorName(name)
										  .supervisorEmail("test@gmail.com")
										  .build();
		supervisorRepo.save(supervisor);
		Assertions.assertThat(supervisorRepo.findBySupervisorName(name).getSupervisorName()).isEqualTo(name);
		supervisorRepo.delete(supervisor);
	}
	
	
	@Test
	void taskDueDate() {
		String dueDate = "1990-09-09";
		Task task = Task.builder()
						.description("some description")
						.dueDate(dueDate)
						.taskType(Type.CHARGE)
						.build();
		
		int size = taskRepo.findByDueDate(dueDate).size();
		taskRepo.save(task);
		Assertions.assertThat(taskRepo.findByDueDate(dueDate).size()).isEqualTo(size + 1);
		taskRepo.delete(task);
	}
	
	
}
