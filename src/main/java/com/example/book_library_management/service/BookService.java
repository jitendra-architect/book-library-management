package com.example.book_library_management.service;

import com.example.book_library_management.dto.BookDTO;
import com.example.book_library_management.entity.Book;
import com.example.book_library_management.entity.Category;
import com.example.book_library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add a new book
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Search books by title
    public List<Book> searchBooksByTitle(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<BookDTO> getBooks(String name) {
        List<Object[]> books = bookRepository.getBooks_Native(name);
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Object[] result : books) {

            String title = (String) result[0];
            Long bookId = (Long) result[1];
            bookDTOs.add(new BookDTO(title, bookId));
        }
        return bookDTOs;
    }
}

