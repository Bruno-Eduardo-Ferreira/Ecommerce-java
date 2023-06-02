package br.edu.utfpr.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String hello() {
        return "API Ecommerce is running!";
    }
    
}
