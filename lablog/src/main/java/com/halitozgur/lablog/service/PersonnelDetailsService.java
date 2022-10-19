package com.halitozgur.lablog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.halitozgur.lablog.model.Personnel;
import com.halitozgur.lablog.model.PersonnelDetails;
import com.halitozgur.lablog.repository.PersonnelRepository;

/**
 * Implementing the UserDetailsService so that when passed in the DAO Authentication Provider, it transfers personnel data.
 * @author User
 *
 */
@Service
public class PersonnelDetailsService implements UserDetailsService{

	@Autowired
	private PersonnelRepository personnelRepo;
	
	/**
	 * Loads user by its given user name
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Personnel personnel = personnelRepo.findByPersonnelUserName(username);
		if(personnel == null) {
			throw new UsernameNotFoundException("Wrong credentials!");
		}
		
		return new PersonnelDetails(personnel);
	}

}
