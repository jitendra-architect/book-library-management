package com.example.book_library_management.controller;

import com.example.book_library_management.entity.Book;
import com.example.book_library_management.entity.Borrow;
import com.example.book_library_management.service.BookService;
import com.example.book_library_management.service.BorrowService;
import com.example.book_library_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class BookLibraryController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BorrowService borrowService;

    // Add a new book
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
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
}

