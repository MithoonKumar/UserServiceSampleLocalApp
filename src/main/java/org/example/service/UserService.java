package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepo;

import java.util.UUID;

public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String addUser(String userName, String department) {
        String userId = UUID.randomUUID().toString();
        User user = new User(userName, department, userId);
        return userRepo.addUser(user);
    }

    public void updateUser(String userId, User user) {
        this.userRepo.updateUser(userId, user);
    }

    public void deleteUser(String userId) {
        this.userRepo.deleteUser(userId);
    }

    public User getUser(String userId) {
        return this.userRepo.getUser(userId);
    }
}
