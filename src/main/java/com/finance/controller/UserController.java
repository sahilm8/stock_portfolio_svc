package com.finance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.finance.model.User;
import com.finance.service.UserService;

import lombok.extern.slf4j.Slf4j;

// TODO: Add JWT authentication.

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {
    @Autowired
    private static UserService userService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public static String home() {
        log.info("Received request to GET /home.");
        return String.format(
                "Stock Portfolio Service%n%n" +
                        "Welcome to the users endpoint, you can make the following requests:%n" +
                        "- POST /new-user%n" +
                        "- GET /get-user%n" +
                        "- GET /does-user-exist%n" +
                        "- DELETE /delete-user%n");
    }

    @PostMapping(value = "/new-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public static String createUser(@RequestBody User user) {
        log.info("Received request to POST /new-user with argument: " + user.toString());
        return userService.createUser(user);
    }

    @GetMapping(value = "/get-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public static String getUser(@RequestBody String email) {
        log.info("Received request to GET /get-user with argument: " + email.trim());
        return userService.getUser(email.trim());
    }

    @DeleteMapping(value = "/delete-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public static String deleteUser(@RequestBody String email) {
        log.info("Received request to DELETE /delete-user with argument: " + email.trim());
        return userService.deleteUser(email.trim());
    }
}
