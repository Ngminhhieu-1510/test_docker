package com.example.demo.repositories;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
    List<Book> findByTitleContaining(String title);

    // Tìm sách có publishDate bằng giá trị cho trước
    List<Book> findByPublishDate(LocalDate publishDate);


}






