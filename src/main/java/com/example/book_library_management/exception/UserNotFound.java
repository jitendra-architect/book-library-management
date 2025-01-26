package com.example.book_library_management.exception;

public class UserNotFound extends RuntimeException {
	public UserNotFound(String message) {
		super(message);
	}
}
