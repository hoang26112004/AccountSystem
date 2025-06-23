package com.example.accountSystem.controller;

import com.example.accountSystem.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.accountSystem.service.UserService;
import java.util.List;
import com.example.accountSystem.Entity.User;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }
}
