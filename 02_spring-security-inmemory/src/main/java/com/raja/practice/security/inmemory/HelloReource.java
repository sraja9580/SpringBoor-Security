package com.raja.practice.security.inmemory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping()
@RestController
public class HelloReource {

    @GetMapping
    public String hello(){
        return "Welcome";
    }
    
    @GetMapping("/user")
    public String helloUser(){
        return "Welcome User";
    }
    
    @GetMapping("/admin")
    public String helloAdmin(){
        return "Welcome Admin";
    }
}
