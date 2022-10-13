package com.halitozgur.lablog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="personnel")
@FieldDefaults(level=AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
public class Personnel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long personnelId;
	
	@Column(name = "personnel_name", nullable=false)
	@NotEmpty(message = "username should not be empty")
	@Size(min=8, message="username should have at least 8 characters")
	String personnelUserName;
	
	@Column(name = "personnel_password", nullable=false)
	@NotEmpty(message = "password should not be empty")
	@Size(min=8, message="password should have at least 8 characters")
	String personnelPassword;
	
	
	@OneToOne
	AssignedEmail personnelEmail;
	
}
