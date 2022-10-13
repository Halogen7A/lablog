package com.halitozgur.lablog.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="task")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long taskId;
	
	@Column(name="description")
	@NotEmpty
	@Size(min=2, message="description should have at least 2 characters")
	String description;
	
	@Column(name="due_date", nullable=true)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	LocalDate dueDate;

}
