package org.example.repository;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

public class DDBClient {

    private DynamoDbClient dynamoDbClient;

    public DDBClient() {
         this.dynamoDbClient = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))  // Pointing to Local DynamoDB
                .region(Region.US_EAST_1) // Use any region (doesn't matter with local DynamoDB)
                .build();
    }

    public DynamoDbClient getDynamoDbClient() {
        return dynamoDbClient;
    }

    public void setDynamoDbClient(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }
}
