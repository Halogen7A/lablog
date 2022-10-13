package com.halitozgur.lablog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class EquipmentExperimentKey implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "equipment_id")
	Long equipmentId;
	
	@Column(name = "experiment_id")
	Long experimentId;

}
