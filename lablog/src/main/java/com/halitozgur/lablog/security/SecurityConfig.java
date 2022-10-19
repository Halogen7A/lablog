package com.halitozgur.lablog.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



/**
 * Spring security configuration file
 * @author User
 *
 */
@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * Define the AuthenticationProvider as a Bean in the spring container for the use of Spring Security 
	 * and return a DAO provider.
	 * @return
	 */
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	/**
	 * Defines the PasswordEncoder as a Bean in the spring container for the use of Spring Security
	 * and encode the passwords.
	 * @return
	 */
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Permitting which authority has access to which pages.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
					.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/home").hasAnyAuthority("ADMIN", "PERSONNEL")
					.antMatchers("/register").hasAuthority("ADMIN")
					.anyRequest().authenticated()
					.and().formLogin();
	}
}
