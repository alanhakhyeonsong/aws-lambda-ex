package me.ramos.lambda.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import me.ramos.lambda.config.AWSConfig;
import me.ramos.lambda.model.Player;
import me.ramos.lambda.request.InsertPlayerRequest;
import me.ramos.lambda.response.InsertPlayerResponse;

public class InsertPlayerHandler implements RequestHandler<InsertPlayerRequest, InsertPlayerResponse> {

    @Override
    public InsertPlayerResponse handleRequest(InsertPlayerRequest request, Context context) {
        AmazonDynamoDB amazonDynamoDB = AWSConfig.initDynamoDbClient();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        Player player = new Player();
        player.setId(request.getId());
        player.setName(request.getName());
        player.setBackNumber(request.getBackNumber());

        dynamoDBMapper.save(player);

        Player retrievedPlayer = dynamoDBMapper.load(Player.class, player.getId());
        return new InsertPlayerResponse(retrievedPlayer.getId(), true);
    }
}
