package com.example.accountSystem.service;
import com.example.accountSystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.accountSystem.Entity.User;
import java.util.*;
@Service
public class UserService {
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }
    public User getUserByUserId(Integer userId) {
        return userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));
    }
    public User updateUser(Integer userId, User  updateUser) {
        User existingUser = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(updateUser.getName());
        existingUser.setEmail(updateUser.getEmail());
        existingUser.setPhone(updateUser.getPhone());
        return userRepo.save(existingUser);
    }



}
