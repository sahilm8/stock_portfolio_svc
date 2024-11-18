package com.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.finance.model.User;
import com.finance.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String home() {
        return String.format(
                "Stock Portfolio Service%n%n" +
                        "Welcome to the users endpoint, you can make the following requests:%n" +
                        "- POST /new-user%n" +
                        "- GET /get-user%n" +
                        "- GET /does-user-exist%n" +
                        "- DELETE /delete-user%n");
    }

    @PostMapping(value = "/new-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/get-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@RequestBody String email) {
        return userService.getUser(email.trim());
    }

    @GetMapping(value = "/does-user-exist", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean doesUserExist(@RequestBody String email) {
        return userService.doesUserExist(email.trim());
    }

    @DeleteMapping(value = "/delete-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return "User deleted successfully";
    }
}
