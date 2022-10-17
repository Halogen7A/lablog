package com.halitozgur.lablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.model.Task;
import com.halitozgur.lablog.repository.TaskRepository;

@Service
public class TaskService implements TaskServiceI{

	@Autowired
	private TaskRepository taskRepo;
	
	@Override
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	@Override
	public Task getTaskById(Long id) {
		return taskRepo.findById(id).get();
	}

	@Override
	public Task addTask(Task task) {
		return taskRepo.save(task);
	}

	@Override
	public Task updateTask(Long id, Task task) {
		Task existingTask = taskRepo.findById(id).get();
		existingTask.setDescription(task.getDescription());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setTaskType(task.getTaskType());
		return taskRepo.save(existingTask);
	}

	@Override
	public void deleteTaskById(Long id) {
		taskRepo.deleteById(id);
		
	}

}
