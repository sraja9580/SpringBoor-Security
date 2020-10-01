package com.raja.practice.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.raja.practice.security.jwt.filter.JwtRequestFilter;

@EnableWebSecurity
public class HelloWorldSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	JwtRequestFilter jwtRequestFilter;

	@Autowired
	HardCodedUserDetailsService userDetailsService;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated()
		//IF WE DONT ADD THE BELOW, THEN SERVER WILL MAINTAIN SESSION,USER DONT NEED TO SEND THE TOKEN FOR THE SECOND REQUEST
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//CALLING OUR JwtRequestFilter BEFORE UsernamePasswordAuthenticationFilter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
}
