package com.example.book_library_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException  {

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<Map<String, String>> userNotFoundException(UserNotFound ex){
		String error = ex.getMessage();
		String message = ex.getMessage();
		Map<String, String> map = new HashMap<>();
		map.put("message", message);
		map.put("error", error);
		return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
	}
}
