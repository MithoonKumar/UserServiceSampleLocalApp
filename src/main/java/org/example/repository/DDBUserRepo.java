package org.example.repository;

import org.example.model.User;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.util.HashMap;
import java.util.Map;

public class DDBUserRepo implements UserRepo {

    private DynamoDbClient dynamoDbClient;

    public DDBUserRepo(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @Override
    public String addUser(User user) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userId", AttributeValue.builder().s(user.getUserId()).build());  // String value
        item.put("userName", AttributeValue.builder().s(user.getUserName()).build());;
        item.put("department", AttributeValue.builder().s(user.getDepartment()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("user")
                .item(item)
                .build();

        PutItemResponse response = dynamoDbClient.putItem(request);
        System.out.println("Item inserted: " + response);
        return user.getUserId();
    }

    @Override
    public User getUser(String userId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.builder().s(userId).build());  // Search by ID

        GetItemRequest request = GetItemRequest.builder()
                .tableName("user")
                .key(key)
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);
        System.out.println("Fetched item: " + response.item());
        Map<String, AttributeValue> item = response.item();

        // Map response to model object (User)
        if (item != null && !item.isEmpty()) {
            String id = item.get("userId").s();      // Extract the "ID" value
            String name = item.get("userName").s();  // Extract the "Name" value
            String department = item.get("department").s();
            return new User(id, name, department);// Create and return the User object
        }
        return null;
    }

    @Override
    public void deleteUser(String userId) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.builder().s(userId).build());  // Assuming "ID" is the primary key

        // Create the DeleteItemRequest
        DeleteItemRequest deleteRequest = DeleteItemRequest.builder()
                .tableName("user")  // Your DynamoDB table name
                .key(key)                   // Key of the item to delete
                .build();

        // Execute the delete request
        DeleteItemResponse deleteResponse = dynamoDbClient.deleteItem(deleteRequest);
        System.out.println("Item deleted successfully: " + deleteResponse);
    }

    @Override
    public void updateUser(String userId, User user) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.builder().s(userId).build());  // Assuming "ID" is the primary key

        // Define the new attribute value (in this case, we are updating "Name")
        Map<String, AttributeValue> updatedValues = new HashMap<>();
        updatedValues.put(":userName", AttributeValue.builder().s(user.getUserName()).build());
        updatedValues.put(":department", AttributeValue.builder().s(user.getDepartment()).build());

        // Define the update expression (which attribute to update)
        String updateExpression = "SET userName = :userName, department = :department";

        // Create the UpdateItemRequest
        UpdateItemRequest updateRequest = UpdateItemRequest.builder()
                .tableName("user")           // Your DynamoDB table name
                .key(key)                            // Key of the item to update
                .updateExpression(updateExpression)  // Update expression
                .expressionAttributeValues(updatedValues)  // Values to use in the expression
                .build();

        // Execute the update request
        UpdateItemResponse updateResponse = dynamoDbClient.updateItem(updateRequest);
        System.out.println("Item updated successfully: " + updateResponse);
    }
}
