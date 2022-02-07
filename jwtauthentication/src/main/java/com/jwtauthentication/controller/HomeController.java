package com.jwtauthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello jwt private page.";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome to private page.";
    }
}
