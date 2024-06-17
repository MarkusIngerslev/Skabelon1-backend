package com.example.skabelon1backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to the Skabelon1 backend!";
    }
}
