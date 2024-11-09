package com.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.finance.model.User;
import com.finance.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "Welcome to the Stock Portfolio Service!";
    }

    @PostMapping("/new-user")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/get-user")
    public User getUser(@RequestBody String email) {
        return userService.getUser(email);
    }

    @GetMapping("/does-user-exist")
    public boolean doesUserExist(@RequestBody String email) {
        return userService.doesUserExist(email);
    }

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }
}
