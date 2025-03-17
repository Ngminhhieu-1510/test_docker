package com.example.demo.dto;

import java.time.LocalDate;

public class BookDTO {
    private Long id;          // Thêm trường id
    private String title;
    private LocalDate publishDate;

    public BookDTO(Long id, String title, LocalDate publishDate) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}