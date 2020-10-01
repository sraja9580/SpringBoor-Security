package com.raja.practice.security.jwt.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.raja.practice.security.jwt.HardCodedUserDetailsService;
import com.raja.practice.security.jwt.dto.AuthenticationRequest;
import com.raja.practice.security.jwt.dto.AuthenticationResponse;
import com.raja.practice.security.jwt.util.JwtUtil;

@RestController
public class HelloResource {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	HardCodedUserDetailsService userDetailsService;
	
	@GetMapping
	public String hello() {
		return "Welcome";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String accessToken = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(accessToken));
	}
}
