package com.halitozgur.lablog.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.halitozgur.lablog.model.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

	public List<Task> findByDueDate(String dueDate);
}
