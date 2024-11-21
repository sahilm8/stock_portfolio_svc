package com.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finance.model.User;
import com.finance.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private static UserRepository userRepository;

    public String createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            log.info("User created: " + user.toString());
            return "User created successfully.";
        }
        log.info("User already exists: " + user.toString());
        return "User with the given email address already exists.";
    }

    public String getUser(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            log.info("User found: " + user.toString());
            return "User found: " + user.toString();
        }
        log.info("User not found: " + email);
        return "User with the given email address does not exist.";
    }

    public String deleteUser(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            userRepository.delete(userRepository.findByEmail(email).get());
            log.info("User deleted: " + email);
            return "User deleted successfully.";
        }
        log.info("User not found: " + email);
        return "User with the given email address does not exist.";
    }
}
