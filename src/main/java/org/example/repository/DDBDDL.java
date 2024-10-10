package org.example.repository;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class DDBDDL {

    private DynamoDbClient dynamoDbClient;

    public DDBDDL(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }


    public void createTable(String tableName, String hashKey) {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName(tableName)
                .keySchema(KeySchemaElement.builder()
                        .attributeName(hashKey)
                        .keyType(KeyType.HASH) // Partition key
                        .build())
                .attributeDefinitions(AttributeDefinition.builder()
                        .attributeName(hashKey)
                        .attributeType(ScalarAttributeType.S) // String attribute
                        .build())
                .provisionedThroughput(ProvisionedThroughput.builder()
                        .readCapacityUnits(10L)
                        .writeCapacityUnits(10L)
                        .build())
                .build();

        // Create table
        dynamoDbClient.createTable(request);
        System.out.println("Table created successfully.");
    }
}
