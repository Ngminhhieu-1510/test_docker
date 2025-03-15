package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Sử dụng IDENTITY cho auto-increment
    private Long id;

    private String title;
    private BigDecimal price;
    private LocalDate publishDate;   // Phải khớp với findByPublishDate(...)
    private String author;
    private LocalDate createDate;

    // Empty constructor (yêu cầu cho JPA)
    public Book() {
    }

    // Constructor có tham số (nếu cần)
    public Book(String title, String author, LocalDate createDate, BigDecimal price, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.createDate = createDate;
        this.price = price;
        this.publishDate = publishDate;
    }

    // Getters and Setters
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

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
