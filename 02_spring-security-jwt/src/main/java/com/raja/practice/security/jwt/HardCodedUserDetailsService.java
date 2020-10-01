package com.raja.practice.security.jwt;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HardCodedUserDetailsService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if("raja".equalsIgnoreCase(username)) {
			return  new User("raja","{noop}test",new ArrayList<GrantedAuthority>());
		}else {
			throw new UsernameNotFoundException(username);
		}		
	}
}
