package com.example.demo.repositories;

import com.example.demo.models.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    BookCategory findByName(String name);
}