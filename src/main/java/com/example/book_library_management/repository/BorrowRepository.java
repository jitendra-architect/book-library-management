package com.example.book_library_management.repository;

import com.example.book_library_management.entity.Book;
import com.example.book_library_management.entity.Borrow;
import com.example.book_library_management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    // Find borrow records by user
    List<Borrow> findByUser(User user);

    // Find borrow records by book
    List<Borrow> findByBook(Book book);

    // Find overdue borrow records (returnDate is null and borrowDate exceeds a given date)
    @Query("SELECT b FROM Borrow b WHERE b.returnDate IS NULL AND b.borrowDate < :dueDate")
    List<Borrow> findOverdueBorrows(@Param("dueDate") LocalDate dueDate);
}