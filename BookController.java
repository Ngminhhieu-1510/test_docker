package com.example.demo.controller;

import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    private final BookService bookService;

    // Constructor injection
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 1) Lấy tất cả sách
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    // 2) Lấy 1 sách theo ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    // 3) Tạo mới (Create)
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    // 4) Cập nhật (Update)
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        // Lấy book cũ
        Book existingBook = bookService.findById(id);
        // Cập nhật trường
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setPublishedDate(updatedBook.getPublishedDate());
        // Lưu
        return bookService.save(existingBook);
    }

    // 5) Xóa (Delete)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
    }
}
    // Endpoint để test kết nối tới DB
    @GetMapping("/test-connection")
    public String testConnection() {
        return bookRepository.testConnection();
    }
}
