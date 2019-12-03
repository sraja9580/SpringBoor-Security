package com.practicer.raja.security.userservice.srvcimpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.practicer.raja.security.userservice.srvcimpl.entity.User;

public class CustomUserDetails implements UserDetails {
	
	private String userName;	
	private String password;
	private List<GrantedAuthority> grantedAuthorityLst;
	private boolean accountNonExpired;		
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	public CustomUserDetails() {
		 
	}	 
	 
	public CustomUserDetails(String userName, String password, List<GrantedAuthority> grantedAuthorityLst,
			boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled) {
		super();
		this.userName = userName;
		this.password = password;
		this.grantedAuthorityLst = grantedAuthorityLst;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}
	
	public CustomUserDetails(User user) {
		super();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.grantedAuthorityLst = Arrays.stream(user.getRoles().split(","))
								   .map(SimpleGrantedAuthority::new)
								   .collect(Collectors.toList());
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = user.isActive();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorityLst;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

}
