package com.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.model.User;
import com.finance.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser == null) {
            User newUser = userRepository.save(user);
            return "User created successfully: " + newUser.toString();
        }
        return "User with the given email address already exists.";
    }

    public String getUser(String email) {
        User existingUser = userRepository.findByEmail(email).orElse(null);
        if (existingUser != null) {
            return "User found: " + existingUser.toString();
        }
        return "User with the given email address does not exist.";
    }

    public String deleteUser(String email) {
        User existingUser = userRepository.findByEmail(email).orElse(null);
        if (existingUser != null) {
            userRepository.delete(existingUser);
            return "User deleted successfully.";
        }
        return "User with the given email address does not exist.";
    }
}
