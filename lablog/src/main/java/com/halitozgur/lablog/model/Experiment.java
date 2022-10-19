package com.halitozgur.lablog.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Experiment POJO class.
 * @author User
 *
 */

@Entity
@Table(name="experiment")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Experiment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long experimentId;
	
	@Column(name = "start_date", nullable=false)
	@NotEmpty
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	String startDate;
	
	@Column(name = "end_date", nullable=true)
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	String endDate;
	
	@Column(name = "damage_cost", columnDefinition = "DECIMAL(4,2) DEFAULT 0.00", nullable=false)
	double damageCost;
	
	
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinTable(
			name = "student_experiment",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "experiment_id")
			)
	Set<Student> students;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="experiment")
	Set<EquipmentOrder> orders;
	
	@JsonIgnore
	@ManyToOne(optional=true)
	Supervisor supervisor;



	
	
}
