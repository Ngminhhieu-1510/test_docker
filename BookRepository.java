package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Phương thức test kết nối DB
    public String testConnection() {
        try {
            // Truy vấn đơn giản để kiểm tra kết nối
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Database connection is successful!";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    // Custom query
    @Query("SELECT b FROM Book b WHERE b.publishDate > :date")
    List<Book> findByPublishedDateAfter(@Param("date") LocalDate date);

}


public interface BookRepository extends JpaRepository<Book, Long> {

    // Tìm theo title
    List<Book> findByTitle(String title);

    // Tìm theo author
    List<Book> findByAuthor(String author);

    // Tìm theo createDate
    List<Book> findByCreateDate(LocalDate createDate);
}
