package com.halitozgur.lablog.model;

import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Equipment POJO class. Equals and Hash Code excluded for the orders to prevent stackoverflowerror
 * @author User
 *
 */
@Entity
@Table(name="equipment")
@FieldDefaults(level=AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude="orders")
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


	@OneToMany(mappedBy="equipment", fetch=FetchType.EAGER)
	Set<EquipmentOrder> orders;
	
	
}
