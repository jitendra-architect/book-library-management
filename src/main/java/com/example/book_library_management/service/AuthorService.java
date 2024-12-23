package com.example.book_library_management.service;

import com.example.book_library_management.entity.Author;
import com.example.book_library_management.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public Author getOrCreateAuthor(String name) {
		return authorRepository.findByName(name)
				.orElseGet(() -> authorRepository.save(new Author(name)));
	}
}
