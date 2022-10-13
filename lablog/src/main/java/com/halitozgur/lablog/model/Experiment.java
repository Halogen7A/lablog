package com.halitozgur.lablog.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="experiment")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class Experiment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long experimentId;
	
	@Column(name = "start_date", nullable=false)
	@NotEmpty
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	String startDate;
	
	@Column(name = "end_date", nullable=true)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	String endDate;
	
	@Column(name = "damage_cost", columnDefinition = "DECIMAL(4,2) DEFAULT 0.00", nullable=false)
	double damageCost;
	
	
	@ManyToMany
	@JoinTable(
			name = "student_experiment",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "experiment_id")
			)
	Set<Student> students;
	
	@OneToMany(mappedBy = "experiment")
	List<EquipmentExperiment> equipments;
	
	@ManyToOne(optional=true)
	@JoinColumn(name = "supervisor_id", nullable=true)
	Supervisor supervisor;

	
}
