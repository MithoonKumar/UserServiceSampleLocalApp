package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.example.validator.RequestValidator;

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String addUser(String userName, String department) {
        return this.userService.addUser(userName, department);
    }

    public void updateUser(String userId, User user) {
        RequestValidator.validateUpdateUserRequest(userId, user);
        this.userService.updateUser(userId, user);
    }

    public void deleteUser(String userId) {
        this.userService.deleteUser(userId);
    }

    public User getUser(String userId) {
        return this.userService.getUser(userId);
    }
}
