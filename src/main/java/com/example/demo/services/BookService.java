package com.example.demo.services;

import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Phương thức tìm book theo id
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id = " + id));
    }
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    // Ví dụ: Phương thức lưu


    // Ví dụ: Lấy danh sách
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Xoá
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    // ... (các phương thức khác nếu cần)
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }



}
