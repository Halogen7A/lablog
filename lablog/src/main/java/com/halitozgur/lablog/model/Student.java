package com.halitozgur.lablog.model;

import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="student")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long studentId;
	
	@Column(name = "student_name", nullable=false)
	@NotEmpty
	@Size(min=2, message="name should have at least 2 characters")
	String studentName;
	
	
	@Column(name = "student_email", nullable=false, unique=true)
	@NotEmpty
	@Email
	String studentEmail;
	
	
	@Column(name = "birth_date", nullable=false)
	@NotEmpty
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	String birthDate;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "students")
	Set<Experiment> experimentStudents;
	
	
	
}
