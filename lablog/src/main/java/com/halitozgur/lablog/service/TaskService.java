package com.halitozgur.lablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.model.Task;
import com.halitozgur.lablog.repository.TaskRepository;

/**
 * Business logic for task.
 * @author User
 *
 */
@Service
public class TaskService implements TaskServiceI{

	@Autowired
	private TaskRepository taskRepo;
	
	/**
	 * Lists out all tasks
	 */
	@Override
	public List<Task> getAllTasks() {
		return taskRepo.findAll();
	}

	/**
	 * Gets the task by the given id.
	 */
	@Override
	public Task getTaskById(Long id) {
		return taskRepo.findById(id).get();
	}

	/**
	 * Adds task.
	 */
	@Override
	public Task addTask(Task task) {
		return taskRepo.save(task);
	}

	/**
	 * Edits the task given by id.
	 */
	@Override
	public Task updateTask(Long id, Task task) {
		Task existingTask = taskRepo.findById(id).get();
		existingTask.setDescription(task.getDescription());
		existingTask.setDueDate(task.getDueDate());
		existingTask.setTaskType(task.getTaskType());
		return taskRepo.save(existingTask);
	}

	/**
	 * Deletes the task given by id.
	 */
	@Override
	public void deleteTaskById(Long id) {
		taskRepo.deleteById(id);
		
	}

}
