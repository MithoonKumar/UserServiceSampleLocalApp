package org.example;

import org.example.controller.UserController;
import org.example.model.User;
import org.example.repository.DDBClient;
import org.example.repository.DDBDDL;
import org.example.repository.DDBUserRepo;
import org.example.service.UserService;

public class Main {

    public static void main(String[] args) {
        DDBClient ddbClient = new DDBClient();
        DDBDDL ddbddl = new DDBDDL(ddbClient.getDynamoDbClient());
        try {
            ddbddl.createTable("user", "userId");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        DDBUserRepo ddbUserRepo = new DDBUserRepo(ddbClient.getDynamoDbClient());
        UserService userService = new UserService(ddbUserRepo);
        UserController userController = new UserController(userService);

        //Add users
        String userId1 = userController.addUser("Mithoon", "IT");
        String userId2 = userController.addUser("Sachin", "Cricket");

        //Get user
        System.out.println(userController.getUser(userId1));
        System.out.println(userController.getUser(userId2));

        //Update user
        User user = new User("Mithon1", "IT1", userId1);
        userController.updateUser(userId1, user);

        //Delete user
        userController.deleteUser(userId2);

        //Get user
        System.out.println(userController.getUser(userId1));
    }
}