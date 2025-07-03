package com.example.accountSystem.service;
import com.example.accountSystem.reponse.UserReponse;
import com.example.accountSystem.repository.UserRepository;
import com.example.accountSystem.requests.AddUserRequest;
import org.springframework.stereotype.Service;
import com.example.accountSystem.entity.User;
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
//    public User updateUser(Integer userId, User  updateUser) {
//        User existingUser = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        existingUser.setName(updateUser.getName());
//        existingUser.setEmail(updateUser.getEmail());
//        existingUser.setPhone(updateUser.getPhone());
//        return userRepo.save(existingUser);
//    }
    public UserReponse addUser(AddUserRequest request) {
        if(userRepo.existsById(request.getId())){
            throw new RuntimeException("ID da ton tai: "+request.getId());
        }
        if(userRepo.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email da ton tai: "+request.getEmail());
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setId(request.getId());
        user.setPhone(request.getPhone());
        User saved = userRepo.save(user);
        return new UserReponse(saved);
    }
}
