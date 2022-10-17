package com.halitozgur.lablog.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="supervisor")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class Supervisor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long supervisorId;
	
	@Column(name = "supervisor_name", nullable=false)
	@NotEmpty
	@Size(min=2, message="name should have at least 2 characters")
	String supervisorName;
	
	
	@Column(name = "supervisor_email", nullable=false, unique=true)
	@NotEmpty
	@Email
	String supervisorEmail;
	

	@OneToMany(cascade = CascadeType.ALL)
	Set<Experiment> experiment;
}
