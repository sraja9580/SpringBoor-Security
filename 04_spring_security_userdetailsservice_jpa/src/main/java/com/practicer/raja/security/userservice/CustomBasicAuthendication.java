package com.practicer.raja.security.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class CustomBasicAuthendication extends WebSecurityConfigurerAdapter {

	@Qualifier("jpaUserService")
	@Autowired	
	UserDetailsService userDetailsService;
	
	
	//AUTHENDICATION START
	/*
	 * WE ARE OVERIDING THIS METHOD TO PROVIDE CUSOTOM AUTHENDICATION.DEFAULT
	 * AUTHENDICATION JUST ALLOWS TO CONFIGURE SINGLE USER
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {				
		auth.userDetailsService(userDetailsService);	 
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
	
	//IT IS USED IF PWD IS NOT ENCODED
	@Bean
	public PasswordEncoder pswdEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}