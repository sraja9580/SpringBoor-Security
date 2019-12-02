package com.practicer.raja.bootsecurity;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class CustomBasicAuthendication extends WebSecurityConfigurerAdapter {

	
	//AUTHENDICATION START
	/*
	 * WE ARE OVERIDING THIS METHOD TO PROVIDE CUSOTOM AUTHENDICATION.DEFAULT
	 * AUTHENDICATION JUST ALLOWS TO CONFIGURE SINGLE USER
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("test")
		.password("{noop}test")
		.roles("USER")
		.and() //Return object so we can add another set of user or other details
		.withUser("test1")
		.password("{noop}test")
		.roles("ADMIN");
	}
	//AUTHENDICATION END
	
	//AURHORIZATION START
	/*
	 * while authorizing we have to start with most restricted url to list
	 * restricted. in the above example if put .antMatchers("/").permitAll() first
	 * other two items will not be verified, every one will access everything so we
	 * have to be careful with authentication orderF
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception  {
		httpSecurity.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/").permitAll()
		.and().formLogin();
	}
	//AURHORIZATION END
}