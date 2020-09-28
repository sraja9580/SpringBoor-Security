package com.raja.practice.springsecurity.springdefaultsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloReource {

    @GetMapping
    public String hello(){
        return "Hello";
    }
}
