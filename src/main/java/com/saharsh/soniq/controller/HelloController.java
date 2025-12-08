package com.saharsh.soniq.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
    
@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
            return "Hello Saharsh! , Saharsh thia side i greet you";
        }
}