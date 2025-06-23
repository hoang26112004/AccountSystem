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




}
