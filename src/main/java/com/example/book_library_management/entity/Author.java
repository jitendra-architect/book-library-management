package com.example.book_library_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(nullable = false)
    private String name;

    @Lob
    private String bio;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)  // Lazy loading for books
    private List<Book> books;

    // Getters and Setters
}