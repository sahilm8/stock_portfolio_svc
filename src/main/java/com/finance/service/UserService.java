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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public boolean doesUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
