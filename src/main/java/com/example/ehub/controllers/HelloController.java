package com.example.ehub.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@Tag(name = "Mockup User Controller")
public class HelloController {
    @GetMapping()
    public String hello() {
        return "Hello world";
    }
}
