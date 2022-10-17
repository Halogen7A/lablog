package com.halitozgur.lablog.service;

import java.util.List;

import com.halitozgur.lablog.model.Task;

public interface TaskServiceI {

	public List<Task> getAllTasks();
	public Task getTaskById(Long id);
	public Task addTask(Task task);
	public Task updateTask(Long id, Task task);
	public void deleteTaskById(Long id);
}
