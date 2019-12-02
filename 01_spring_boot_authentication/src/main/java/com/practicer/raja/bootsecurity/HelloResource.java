package com.practicer.raja.bootsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloResource {
	
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
}
