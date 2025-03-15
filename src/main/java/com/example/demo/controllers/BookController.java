package com.example.demo.controllers;

import com.example.demo.models.Book;
import com.example.demo.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    // Constructor injection để Spring tự tiêm bean BookService
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }




    // 1. Lấy danh sách tất cả sách
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookService.findAll();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving books: " + e.getMessage());
        }
    }

    // 2. Lấy thông tin sách theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Book book = bookService.findById(id);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Book not found: " + e.getMessage());
        }
    }

    // 3. Tạo mới sách
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.save(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating book: " + e.getMessage());
        }
    }

    // 4. Cập nhật sách (sử dụng PUT với path variable ID)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        try {
            // Tìm sách theo ID
            Optional<Book> optionalBook = bookService.getBookById(id);
            if (optionalBook.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found.");
            }

            // Lấy sách cũ và cập nhật thông tin
            Book existingBook = optionalBook.get();
            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setAuthor(bookDetails.getAuthor());
            existingBook.setPublishDate(bookDetails.getPublishDate());
            existingBook.setPrice(bookDetails.getPrice());

            // Lưu sách đã cập nhật
            Book updatedBook = bookService.save(existingBook);
            return ResponseEntity.ok(updatedBook);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating book: " + e.getMessage());
        }
    }


    // 5. Xoá sách theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting book: " + e.getMessage());
        }
    }
    @RequestMapping("/products")
    public class ProductController {
        private BookService bookService;  // Inject BookService


    }



}
