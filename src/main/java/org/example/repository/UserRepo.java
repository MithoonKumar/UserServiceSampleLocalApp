package org.example.repository;

import org.example.model.User;

public interface UserRepo {

    String addUser(User user);

    User getUser(String userId);

    void deleteUser(String userId);

    void updateUser(String userId, User user);


}
