package com.sionsmith.aws;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import io.dropwizard.lifecycle.Managed;

/**
 * Created by sionsmith on 13/09/2014.
 */
public class DynamoDBManaged implements Managed {
    private AmazonDynamoDB dynamoDB;

    public DynamoDBManaged(AmazonDynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
        this.dynamoDB.setRegion(AWSUtils.parseRegion("eu-west-1"));
    }

    @Override
    public void start() throws Exception {

    }

    @Override
    public void stop() throws Exception {
        dynamoDB.shutdown();
    }

    public void createTableIfNotExist(String name){
        DynamoDBUtils dynamoDBUtils = new DynamoDBUtils(dynamoDB);
        dynamoDBUtils.createCountTableIfNotExists(name);
    }
}
