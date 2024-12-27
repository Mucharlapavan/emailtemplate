package com.example.emailtemplate.infrastructure.controller;

import com.example.emailtemplate.infrastructure.domain.sql.model.User;
import com.example.emailtemplate.infrastructure.domain.sql.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // Register the user, save them to the database, and send email
        return userService.registerUser(user);
    }
}
