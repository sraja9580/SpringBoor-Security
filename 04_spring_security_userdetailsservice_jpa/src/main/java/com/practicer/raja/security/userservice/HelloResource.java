package com.practicer.raja.security.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping("/user")
	public String hellouser() {
		return "Hello USER";
	}
	
	@GetMapping("/admin")
	public String helloadmin() {
		return "Hello ADMIN";
	}
}
