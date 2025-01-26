package com.example.book_library_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Book")
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "ISBN is mandatory")
    @Size(max = 13, message = "ISBN cannot exceed 13 characters")
    @Column(nullable = false, unique = true)
    private String isbn;

    @PastOrPresent(message = "Published date cannot be in the future")
    private LocalDate publishedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Book_Author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;
    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookId, book.bookId) &&
                Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, isbn);
    }

    public Book(){}

    public Book(Long bookId) {
        this.bookId = bookId;
    }

    public Book(Long bookId, String title, String isbn, LocalDate publishedDate, Category category, List<Author> authors, LocalDate createdAt, LocalDate updatedAt) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.category = category;
        this.authors = authors;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //Getter and Setter
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    // toString (Avoid lazy-loading issues)
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishedDate=" + publishedDate +
                '}';
    }

    // Builder pattern (optional)
    public static class Builder {
        private final Book book;

        public Builder() {
            book = new Book();
        }

        public Builder withTitle(String title) {
            book.setTitle(title);
            return this;
        }

        public Builder withIsbn(String isbn) {
            book.setIsbn(isbn);
            return this;
        }

        public Builder withPublishedDate(LocalDate publishedDate) {
            book.setPublishedDate(publishedDate);
            return this;
        }

        public Builder withCategory(Category category) {
            book.setCategory(category);
            return this;
        }

        public Builder withAuthors(List<Author> authors) {
            book.setAuthors(authors);
            return this;
        }

        public Book build() {
            return book;
        }
    }
}