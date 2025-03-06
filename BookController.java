package com.example.demo.controller;

import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Endpoint để test kết nối tới DB
    @GetMapping("/test-connection")
    public String testConnection() {
        return bookRepository.testConnection();
    }
}
