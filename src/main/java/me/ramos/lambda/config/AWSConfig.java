package me.ramos.lambda.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class AWSConfig {

    private AWSConfig() {
    }

    private static final String DYNAMODB_TABLE_NAME = "players";
    private static final Regions REGION = Regions.AP_NORTHEAST_2;

    private static AmazonDynamoDB amazonDynamoDB;

    public static synchronized AmazonDynamoDB initDynamoDbClient() {
        if (amazonDynamoDB == null) {
            amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
                    .withRegion(REGION)
                    .build();
        }
        return amazonDynamoDB;
    }

    public static String getDynamodbTableName() {
        return DYNAMODB_TABLE_NAME;
    }
}
