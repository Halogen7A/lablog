package com.halitozgur.lablog.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="equipment")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class Equipment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long equipmentId;
	
	@Column(name = "equipment_name", nullable=false)
	@NotEmpty
	@Size(min=2, message="name should have at least 2 characters")
	String equipName;
	
	@Column(name = "available_quantity", nullable=false)
	@Min(0)
	int availableQuantity;
	
	@Column(name = "unit_price", nullable=false)
	@DecimalMin("0.0")
	double unitPrice;
	
	@JsonIgnore
	@OneToMany(mappedBy = "equipment")
	List<EquipmentExperiment> quantityUsed;

	
	
	
}
