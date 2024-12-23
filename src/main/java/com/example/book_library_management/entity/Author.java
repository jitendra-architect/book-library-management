package com.example.book_library_management.entity;

import jakarta.persistence.*;

import java.util.List;

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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public Author(Long authorId, String name, String bio, List<Book> books) {
        this.authorId = authorId;
        this.name = name;
        this.bio = bio;
        this.books = books;
    }
}