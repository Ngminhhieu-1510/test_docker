package com.example.demo.controllers;

import com.example.demo.dto.BookDTO;
import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDate) {
        try {
            List<BookDTO> books;
            if (title != null && !title.isEmpty() && publishDate != null) {
                books = bookService.findByTitleAndPublishDate(title, publishDate);
            } else if (title != null && !title.isEmpty()) {
                books = bookService.findByTitle(title);
            } else if (publishDate != null) {
                books = bookService.findByPublishDate(publishDate);
            } else {
                books = bookService.getAllBooks();
            }
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi lấy danh sách sách: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Optional<Book> book = bookService.getBookById(id);
            if (book.isPresent()) {
                return ResponseEntity.ok(book.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy sách.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi lấy thông tin sách: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi tạo sách: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            return ResponseEntity.ok(updatedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi cập nhật sách: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Lỗi khi xóa sách: " + e.getMessage());
        }
    }
}