package com.halitozgur.lablog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.halitozgur.lablog.model.Task;
import com.halitozgur.lablog.repository.TaskRepository;
import com.halitozgur.lablog.service.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * CRUD operations on task, search by due date.
 * @author User
 *
 */
@Controller
@Slf4j
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepository taskRepo;
	
	/**
	 * Returns the tasks with the given due date.
	 * @param dueDate
	 * @param model
	 * @return
	 */
	@GetMapping("/task/search")
	public String findByDueDate(@RequestParam(name="dueDate") String dueDate, Model model) {
		log.info("finding due date for input {dueDate}");
		model.addAttribute("task", taskRepo.findByDueDate(dueDate));
		log.info("found!");
		return "task";
	}
	
	/**
	 * List all tasks.
	 * @param model
	 * @return
	 */
	@GetMapping("/task")
	public String listTasks(Model model) {
		model.addAttribute("task", taskService.getAllTasks());
		return "task";
	}
	
	/**
	 * Adds a new task to the model and forwards the user to create_task.html.
	 * @param model
	 * @return
	 */
	@GetMapping("/task/new")
	public String createAddTaskPage(Model model) {
		Task task = new Task();
		model.addAttribute("task", task);
		return "create_task";
	}
	
	/**
	 * Adds the task to task table.
	 * @param task
	 * @return
	 */
	@PostMapping("/task/added")
	public String addTask(@ModelAttribute("task") Task task) {
		taskService.addTask(task);
		return "redirect:/task";
	}
	
	/**
	 * Adds the given task to the model and forwards the use to "edit_task.html".
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/task/edit/{id}")
	public String createEditPage(@PathVariable Long id, Model model) {
		model.addAttribute("task", taskService.getTaskById(id));
		return "edit_task";
	}
	
	/**
	 * Edits and updates the task.
	 * @param id
	 * @param task
	 * @return
	 */
	@PostMapping("/task/updated/{id}")
	public String updateTask(@PathVariable Long id, @ModelAttribute("task") Task task) {
		taskService.updateTask(id, task);
		return "redirect:/task";
	}
	
	/**
	 * Deletes the given task.
	 * @param id
	 * @return
	 */
	@GetMapping("/task/delete/{id}")
	public String deleteTask(@PathVariable Long id) {
		taskService.deleteTaskById(id);
		return "redirect:/task";
	}
	
}
