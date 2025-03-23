package com.example.demo.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String author;
    private LocalDate publishDate;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private BookCategory category;

    // Constructor
    public Book(String title, String author, LocalDate publishDate, Double price, BookCategory category) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.price = price;
        this.category = category;
    }

    // Constructor mặc định được yêu cầu bởi JPA
    public Book() {}

    // Getters và setters
    public Long getId() {
        return id; }
    public void setId(Long id) {
        this.id = id; }
    public String getTitle() {
        return title; }
    public void setTitle(String title) {
        this.title = title; }
    public String getAuthor() {
        return author; }
    public void setAuthor(String author) {
        this.author = author; }
    public LocalDate getPublishDate() {
        return publishDate; }
    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate; }
    public Double getPrice() {
        return price; }
    public void setPrice(Double price) {
        this.price = price; }
    public BookCategory getCategory() {
        return category; }
    public void setCategory(BookCategory category) {
        this.category = category; }
}