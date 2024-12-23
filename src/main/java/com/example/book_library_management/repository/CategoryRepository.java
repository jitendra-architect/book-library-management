package com.example.book_library_management.repository;

import com.example.book_library_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find category by name
    Category findByName(String name);
}
