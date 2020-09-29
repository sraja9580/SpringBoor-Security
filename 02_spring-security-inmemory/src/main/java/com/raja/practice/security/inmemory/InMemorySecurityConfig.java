package com.raja.practice.security.inmemory;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter{
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("raja")
		.password("{noop}test")
		.roles("ADMIN")
		.and()
		.withUser("srini")
		.password("{noop}test")
		.roles("USER");
	}
	
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  .antMatchers("/admin").hasRole("ADMIN")
	  .antMatchers("/user").hasAnyRole("USER","ADMIN")
	  .antMatchers("/").permitAll()
	  .and().formLogin();
	}
	
}
