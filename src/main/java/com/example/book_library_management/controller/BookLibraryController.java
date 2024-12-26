package com.example.book_library_management.controller;

import com.example.book_library_management.dto.BookDTO;
import com.example.book_library_management.dto.BookRequest;
import com.example.book_library_management.entity.Author;
import com.example.book_library_management.entity.Book;
import com.example.book_library_management.entity.Borrow;
import com.example.book_library_management.entity.Category;
import com.example.book_library_management.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
public class BookLibraryController {

    Logger logger = LoggerFactory.getLogger(BookLibraryController.class);

    private final BookService bookService;

    private final BorrowService borrowService;

    private final CategoryService categoryService;

    private final AuthorService authorService;

    public BookLibraryController(BookService bookService, BorrowService borrowService, CategoryService categoryService, AuthorService authorService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
        this.categoryService = categoryService;
        this.authorService = authorService;
    }

    // Add a new book
    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        // Create a Category
        Category category = categoryService.getOrCreateCategory(bookRequest.getCategoryName());
        logger.info("Jitendra {}", category.toString());
        // Create Authors and associate them with the book
        List<Author> authors = bookRequest.getAuthors().stream()
                .map(authorService::getOrCreateAuthor)
                .collect(Collectors.toList());

        // Create Book
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setIsbn(bookRequest.getIsbn());
        book.setPublishedDate(bookRequest.getPublishedDate());
        book.setCategory(category);
        book.setAuthors(authors);
        // Save Book
        Book savedBook = bookService.saveBook(book);
        logger.info("Jitendra {}", savedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // Get all books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Search books by title
    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.searchBooksByTitle(keyword));
    }

    // Borrow a book
    @PostMapping("/borrow")
    public ResponseEntity<Borrow> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
        return ResponseEntity.ok(borrowService.borrowBook(userId, bookId));
    }

    // Return a book
    @PostMapping("/return")
    public ResponseEntity<Borrow> returnBook(@RequestParam Long borrowId) {
        return ResponseEntity.ok(borrowService.returnBook(borrowId));
    }

    // Get borrow history for a user
    @GetMapping("/borrow/user/{userId}")
    public ResponseEntity<List<Borrow>> getBorrowHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(borrowService.getBorrowHistory(userId));
    }

    @GetMapping("/book/search/{name}")
    public ResponseEntity<List<BookDTO>> getBooksByName(@PathVariable String name) {
        return ResponseEntity.ok(bookService.getBooks(name));
    }
}

