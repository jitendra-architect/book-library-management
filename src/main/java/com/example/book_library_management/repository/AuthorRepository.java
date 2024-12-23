package com.example.book_library_management.repository;

import com.example.book_library_management.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Find authors by name containing a keyword (case-insensitive)
    List<Author> findByNameContainingIgnoreCase(String keyword);
}
