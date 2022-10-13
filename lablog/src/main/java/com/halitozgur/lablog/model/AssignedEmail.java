package com.halitozgur.lablog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="assigned_email")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class AssignedEmail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long emailId;

	@Column(name = "personel_email", nullable=false, unique=true)
	@NotEmpty
	@Email
	String email;
	
	@Column(name = "is_used", nullable=false)
	boolean isUsed = false;
	
	@OneToOne(mappedBy="personnelEmail")
	Personnel personnel;
}
