package com.nath.codeworks.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

/**
 * @author ghtvnath
 * 
 * This security configuration filters all the requests that come for /rest end-point.
 * If not authenticated and authorized with correct username and password, API invocation 
 * will not be allowed and Authorization error response will be returned. 
 */
@Component
@EnableWebSecurity
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiSecurityConfig.class);
	
	@Autowired
	ApiAuthEntryPoint apiAuthEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()                                                                
			.antMatchers("/rest/**").hasRole("USER")                                      
			.anyRequest().authenticated();
		
		http.httpBasic().authenticationEntryPoint(apiAuthEntryPoint);
		
		http.sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		LOGGER.info("Configuring Authentication Manager");
		// for demo purposes, in-memory authentication is used with default username and password
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

}
