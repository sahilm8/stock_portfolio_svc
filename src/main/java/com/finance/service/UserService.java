package com.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finance.model.User;
import com.finance.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public String createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty()) {
            userRepository.save(user);
            return "User created successfully.";
        }
        return "User with the given email address already exists.";
    }

    public String getUser(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "User found: " + userRepository.findByEmail(email).get().toString();
        }
        return "User with the given email address does not exist.";
    }

    public String deleteUser(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            userRepository.delete(userRepository.findByEmail(email).get());
            return "User deleted successfully.";
        }
        return "User with the given email address does not exist.";
    }
}
