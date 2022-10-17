package com.halitozgur.lablog.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class PersonnelDetails implements UserDetails{
	
	
	private Personnel personnel;
	

	public PersonnelDetails(Personnel personnel) {
		super();
		this.personnel = personnel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return Collections.singleton(new SimpleGrantedAuthority(personnel.getRole()));
	}

	@Override
	public String getPassword() {

		return personnel.getPersonnelPassword();
	}

	@Override
	public String getUsername() {

		return personnel.getPersonnelUserName();
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
