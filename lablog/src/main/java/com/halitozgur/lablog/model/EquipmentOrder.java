package com.halitozgur.lablog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Join table for the many to many relationship of Experiment and Equipment with the additional attribute of quantity used.
 * @author User
 *
 */
@Entity
@Table(name = "equipment_order")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode(exclude="experiment")
public class EquipmentOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long orderId;
	
	
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	Equipment equipment;
	
	
	@ManyToOne
	@JoinColumn(name = "experiment_id")
	@OnDelete(action=OnDeleteAction.CASCADE)
	Experiment experiment;
	
	@Column(name = "quantity_used", columnDefinition = "int DEFAULT 1")
	int quantityUsed;

	
	
}
