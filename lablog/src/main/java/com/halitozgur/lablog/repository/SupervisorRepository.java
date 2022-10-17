package com.halitozgur.lablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.halitozgur.lablog.model.Supervisor;

@RepositoryRestResource(collectionResourceRel = "supervisor", path = "supervisor")
public interface SupervisorRepository extends JpaRepository<Supervisor, Long>{

	public Supervisor findBySupervisorName(String supervisorName);
}
