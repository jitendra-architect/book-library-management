package com.example.book_library_management.repository;

import com.example.book_library_management.entity.Book;
import com.example.book_library_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by category
    List<Book> findByCategory(Category category);

    // Find books by title containing a keyword (case-insensitive)
    List<Book> findByTitleContainingIgnoreCase(String keyword);

    // Find books by author
    List<Book> findByAuthorsName(String authorName);
}