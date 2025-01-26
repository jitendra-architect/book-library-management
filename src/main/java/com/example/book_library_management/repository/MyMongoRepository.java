package com.example.book_library_management.repository;

import com.example.book_library_management.entity.MyMongoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyMongoRepository extends MongoRepository<MyMongoDocument, String> {
	void findAllById(String id);
}
