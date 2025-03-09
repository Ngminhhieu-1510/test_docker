package com.example.demo.repositories;

import com.example.demo.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    // Custom query
    @Query("SELECT b FROM Book b WHERE b.publishDate > :date")
    List<Book> findByPublishedDateAfter(@Param("date") LocalDate date);


    List<Book> findByAuthor(String author);          // Cần có
    // Tìm sách chứa từ khóa trong title
    List<Book> findByTitleContaining(String title);

    // Tìm sách chứa từ khóa trong author
    List<Book> findByAuthorContaining(String author);

    // Tìm sách theo createDate chính xác (nếu cần có tìm kiếm chính xác ngày)
    List<Book> findByCreateDate(LocalDate createDate);
}


