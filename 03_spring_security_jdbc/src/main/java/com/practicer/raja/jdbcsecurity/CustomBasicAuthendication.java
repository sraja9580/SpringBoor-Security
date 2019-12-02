package com.practicer.raja.jdbcsecurity;

import javax.annotation.sql.DataSourceDefinition;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class CustomBasicAuthendication extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	
	//AUTHENDICATION START
	/*
	 * WE ARE OVERIDING THIS METHOD TO PROVIDE CUSOTOM AUTHENDICATION.DEFAULT
	 * AUTHENDICATION JUST ALLOWS TO CONFIGURE SINGLE USER
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username,password,enabled from users where username=?")
		.authoritiesByUsernameQuery("select username,authority from authorities where username=?");
		//IT IS NOT REQUIRED IF WE CREATE TABLE EXPLICITLY
		/*
		 * .withDefaultSchema()
		 * .withUser(users.username("user").password("password").roles("USER"))
		 * .withUser(users.username("admin").password("password").roles("USER","ADMIN"))
		 */
        ;
		 
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