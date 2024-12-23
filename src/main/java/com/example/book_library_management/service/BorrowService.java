package com.example.book_library_management.service;

import com.example.book_library_management.entity.Book;
import com.example.book_library_management.entity.Borrow;
import com.example.book_library_management.entity.User;
import com.example.book_library_management.repository.BookRepository;
import com.example.book_library_management.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookRepository bookRepository;

    // Borrow a book
    public Borrow borrowBook(Long userId, Long bookId) {
        User user = userService.getUserById(userId);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found!"));

        Borrow borrow = new Borrow();
        borrow.setUser(user);
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDate.now());

        return borrowRepository.save(borrow);
    }

    // Return a book
    public Borrow returnBook(Long borrowId) {
        Borrow borrow = borrowRepository.findById(borrowId)
                .orElseThrow(() -> new RuntimeException("Borrow record not found!"));

        borrow.setReturnDate(LocalDate.now());
        return borrowRepository.save(borrow);
    }

    // Get borrow history for a user
    public List<Borrow> getBorrowHistory(Long userId) {
        User user = userService.getUserById(userId);
        return borrowRepository.findByUser(user);
    }
}


