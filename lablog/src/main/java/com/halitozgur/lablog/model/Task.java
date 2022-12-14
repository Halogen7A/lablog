package com.halitozgur.lablog.model;

/**
 * Task POJO class.
 * @author User
 *
 */

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="task")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long taskId;
	
	@Column(name="task_type", nullable=false)
	Type taskType;
	
	@Column(name="description")
	@NotEmpty
	@Size(min=2, message="description should have at least 2 characters")
	String description;
	
	@Column(name="due_date", nullable=true)
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	String dueDate;

}
