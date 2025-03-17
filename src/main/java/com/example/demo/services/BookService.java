package com.example.demo.services;

import com.example.demo.dto.BookDTO;
import com.example.demo.models.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Lấy danh sách tất cả sách dưới dạng DTO
    public List<BookDTO> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            return books.stream()
                    .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getPublishDate()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sách: " + e.getMessage());
        }
    }

    // Lấy sách theo ID
    public Optional<Book> getBookById(Long id) {
        try {
            return bookRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy thông tin sách: " + e.getMessage());
        }
    }

    // Tạo sách mới
    public Book createBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo sách: " + e.getMessage());
        }
    }

    // Cập nhật sách theo ID
    public Book updateBook(Long id, Book bookDetails) {
        try {
            return bookRepository.findById(id).map(existingBook -> {
                existingBook.setTitle(bookDetails.getTitle());
                existingBook.setAuthor(bookDetails.getAuthor());
                existingBook.setPublishDate(bookDetails.getPublishDate());
                existingBook.setPrice(bookDetails.getPrice());
                return bookRepository.save(existingBook);
            }).orElseThrow(() -> new RuntimeException("Không tìm thấy sách với id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật sách: " + e.getMessage());
        }
    }

    // Xóa sách theo ID
    public void deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa sách: " + e.getMessage());
        }
    }

    // Tìm sách theo title và trả về DTO
    public List<BookDTO> findByTitle(String title) {
        try {
            List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
            return books.stream()
                    .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getPublishDate()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm kiếm sách theo tiêu đề: " + e.getMessage());
        }
    }

    // Tìm sách theo publishDate và trả về DTO
    public List<BookDTO> findByPublishDate(LocalDate publishDate) {
        try {
            List<Book> books = bookRepository.findByPublishDate(publishDate);
            return books.stream()
                    .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getPublishDate()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm kiếm sách theo ngày xuất bản: " + e.getMessage());
        }
    }

    // Tìm sách theo title và publishDate và trả về DTO
    public List<BookDTO> findByTitleAndPublishDate(String title, LocalDate publishDate) {
        try {
            List<Book> books = bookRepository.findByTitleContainingIgnoreCaseAndPublishDate(title, publishDate);
            return books.stream()
                    .map(book -> new BookDTO(book.getId(), book.getTitle(), book.getPublishDate()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm kiếm sách theo tiêu đề và ngày xuất bản: " + e.getMessage());
        }
    }
}