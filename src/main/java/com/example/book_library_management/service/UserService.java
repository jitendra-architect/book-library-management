package com.example.book_library_management.service;

import com.example.book_library_management.entity.MyMongoDocument;
import com.example.book_library_management.entity.User;
import com.example.book_library_management.exception.UserNotFound;
import com.example.book_library_management.repository.MyMongoRepository;
import com.example.book_library_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyMongoRepository myMongoRepository;

    // Get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public List<MyMongoDocument> getAllUsers() {
        return myMongoRepository.findAll();
    }

    public MyMongoDocument getMangoUser(String id) {
        return myMongoRepository.findById(id).orElseThrow(() -> new UserNotFound("User not found!"));
    }
    public MyMongoDocument saveUser(MyMongoDocument user) {
        return myMongoRepository.save(user);
    }
}

