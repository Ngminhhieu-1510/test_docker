package com.example.demo.repositories;

import com.example.demo.dto.BookDTO;
import com.example.demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Tìm kiếm sách theo title chứa từ khoá (không phân biệt chữ hoa thường)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // Tìm sách có publishDate bằng giá trị cho trước
    List<Book> findByPublishDate(LocalDate publishDate);

    // Kết hợp: tìm sách theo title chứa từ khoá và publishDate bằng giá trị cho trước
    List<Book> findByTitleContainingIgnoreCaseAndPublishDate(String title, LocalDate publishDate);
    List<Book> findByCategoryId(Long categoryId);
}


