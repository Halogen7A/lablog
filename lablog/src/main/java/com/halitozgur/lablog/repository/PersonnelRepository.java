package com.halitozgur.lablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.halitozgur.lablog.model.Personnel;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long>{
	
	public Personnel findByPersonnelUserName(String personnelUserName);
}
