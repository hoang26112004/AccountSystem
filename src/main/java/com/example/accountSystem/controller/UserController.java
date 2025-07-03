package com.example.accountSystem.controller;

import com.example.accountSystem.reponse.UserReponse;
import com.example.accountSystem.requests.AddUserRequest;
import com.example.accountSystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.accountSystem.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/add")
    public ResponseEntity<UserReponse> addUser(@Valid @RequestBody AddUserRequest request) {
        return ResponseEntity.ok(userService.addUser(request));
    }
    @GetMapping("/")
    public List<User> getAll() {
        return userService.getAll();
    }
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return userService.getUserByUserId(userId);
    }
}
