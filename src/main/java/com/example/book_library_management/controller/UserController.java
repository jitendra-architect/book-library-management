package com.example.book_library_management.controller;

import com.example.book_library_management.entity.MyMongoDocument;
import com.example.book_library_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/all")
	public ResponseEntity<List<MyMongoDocument>> getAllUsers() {
		List<MyMongoDocument> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MyMongoDocument> getUser(@PathVariable String id) {
		MyMongoDocument users = userService.getMangoUser(id);
		return ResponseEntity.ok(users);
	}

	@PostMapping
	public ResponseEntity<MyMongoDocument> createUser(@RequestBody MyMongoDocument user) {
		MyMongoDocument savedUser = userService.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}
}
