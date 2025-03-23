package com.example.demo.services;

import com.example.demo.dto.BookDTO;
import com.example.demo.models.Book;
import com.example.demo.models.BookCategory;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.BookCategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookCategoryRepository bookCategoryRepository;

    public BookService(BookRepository bookRepository, BookCategoryRepository bookCategoryRepository) {
        this.bookRepository = bookRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

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

    public Optional<Book> getBookById(Long id) {
        try {
            return bookRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy thông tin sách: " + e.getMessage());
        }
    }

    public Book createBook(Book book) {
        try {
            // Kiểm tra và thiết lập category
            if (book.getCategory() != null && book.getCategory().getId() != null) {
                BookCategory category = bookCategoryRepository.findById(book.getCategory().getId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với id: " + book.getCategory().getId()));
                book.setCategory(category);
            }
            return bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo sách: " + e.getMessage());
        }
    }

    public Book updateBook(Long id, Book bookDetails) {
        try {
            return bookRepository.findById(id).map(existingBook -> {
                existingBook.setTitle(bookDetails.getTitle());
                existingBook.setAuthor(bookDetails.getAuthor());
                existingBook.setPublishDate(bookDetails.getPublishDate());
                existingBook.setPrice(bookDetails.getPrice());
                if (bookDetails.getCategory() != null && bookDetails.getCategory().getId() != null) {
                    BookCategory category = bookCategoryRepository.findById(bookDetails.getCategory().getId())
                            .orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục với id: " + bookDetails.getCategory().getId()));
                    existingBook.setCategory(category);
                }
                return bookRepository.save(existingBook);
            }).orElseThrow(() -> new RuntimeException("Không tìm thấy sách với id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật sách: " + e.getMessage());
        }
    }

    public void deleteBook(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa sách: " + e.getMessage());
        }
    }

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