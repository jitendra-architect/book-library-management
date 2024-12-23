package com.example.book_library_management.service;

import com.example.book_library_management.entity.Category;
import com.example.book_library_management.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category getOrCreateCategory(String name) {
		return categoryRepository.findByName(name)
				.orElseGet(() -> categoryRepository.save(new Category(name)));
	}
}