package com.example.book_library_management.entity;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users-document")
public class MyMongoDocument {

	@Id
	private String id;
	private String name;
	private String message;

	public MyMongoDocument(String id, String name, String message) {
		this.id = id;
		this.name = name;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
