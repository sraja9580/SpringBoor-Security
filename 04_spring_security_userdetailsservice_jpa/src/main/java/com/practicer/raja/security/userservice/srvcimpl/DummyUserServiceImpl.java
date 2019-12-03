package com.practicer.raja.security.userservice.srvcimpl;

 

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DummyUserServiceImpl implements  UserDetailsService {
	private static final  Map<String,CustomUserDetails> userDetailsMap = new HashMap<String,CustomUserDetails>();
	
	static{
		
		CustomUserDetails userDetails = new CustomUserDetails("raja","test",Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),new SimpleGrantedAuthority("ROLE_ADMIN")),true,true,true,true) ;
		CustomUserDetails userDetails1 = new CustomUserDetails("arun","test",Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")),true,true,true,true);		
		userDetailsMap.put("raja",userDetails);
		userDetailsMap.put("arun",userDetails1);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		 
		return userDetailsMap.get(username);
	}

}
