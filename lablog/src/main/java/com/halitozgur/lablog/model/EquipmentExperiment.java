package com.halitozgur.lablog.model;



import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;



import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "equipment_experiment")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
public class EquipmentExperiment {

	@EmbeddedId
	EquipmentExperimentKey id;
	
	@ManyToOne
	@MapsId("equipmentId")
	@JoinColumn(name = "equipment_id")
	Equipment equipment;
	
	@ManyToOne
	@MapsId("experimentId")
	@JoinColumn(name = "experiment_id")
	Experiment experiment;
	
	@Column(name = "quantity_used", columnDefinition = "int DEFAULT 1")
	int quantityUsed;
}
