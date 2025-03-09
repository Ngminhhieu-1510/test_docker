package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@RestController
// CRUD: Create - PostMapping, Request (Get, List) - GetMapping, Update - PutMapping, Delete - DeleteMapping
public class HelloController {

    @GetMapping("/hello")  // map request with its handler
    public String hello() {
        return "Hello from Spring Boot!";
    }
}
