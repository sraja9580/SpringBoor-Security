package com.practicer.raja.bootsecurity;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class CustomBasicAuthendication extends WebSecurityConfigurerAdapter {

	/*
	 * WE ARE OVERIDING THIS METHOD TO PROVIDE CUSOTOM AUTHENDICATION.DEFAULT
	 * AUTHENDICATION JUST ALLOWS TO CONFIGURE SINGLE USER
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("test")
		.password("{noop}test")
		.roles("User")
		.and() //Return object so we can add another set of user or other details
		.withUser("test1")
		.password("{noop}test")
		.roles("Admin");
	}
}