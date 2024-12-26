package com.example.book_library_management.dto;

import com.example.book_library_management.entity.Category;

public class BookDTO {
    private String title;
    private Long bookId;

    public BookDTO(String title, Long bookId) {
        this.title = title;
        this.bookId = bookId;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}