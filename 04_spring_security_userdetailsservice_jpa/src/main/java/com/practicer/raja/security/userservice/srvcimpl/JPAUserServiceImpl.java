package com.practicer.raja.security.userservice.srvcimpl;

 

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.practicer.raja.security.userservice.srvcimpl.entity.User;
import com.practicer.raja.security.userservice.srvcimpl.ropos.UserRepository;

@Service("jpaUserService")
public class JPAUserServiceImpl implements  UserDetailsService {
	 
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {		 
		 
		Optional<User> userOptional = userRepo.findByUserName(userName);
		if(!userOptional.isPresent()) {
			throw new UsernameNotFoundException("USER NOT FOUND");
		}
		 return new CustomUserDetails(userOptional.get());
	}

}
