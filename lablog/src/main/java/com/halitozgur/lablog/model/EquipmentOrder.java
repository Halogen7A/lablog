package com.halitozgur.lablog.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	Equipment equipment;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "experiment_id")
	Experiment experiment;
	
	@Column(name = "quantity_used", columnDefinition = "int DEFAULT 1")
	int quantityUsed;

	
	
}
