package me.ramos.lambda.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.ArrayList;
import java.util.List;
import me.ramos.lambda.config.AWSConfig;
import me.ramos.lambda.request.GetPlayerRequest;
import me.ramos.lambda.response.GetPlayerResponse;

public class GetAllPlayerHandler implements RequestHandler<GetPlayerRequest, List<GetPlayerResponse>> {

    @Override
    public List<GetPlayerResponse> handleRequest(GetPlayerRequest request, Context context) {
        AmazonDynamoDB amazonDynamoDB = AWSConfig.initDynamoDbClient();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDB.getTable(AWSConfig.getDynamodbTableName());

        ScanSpec scanSpec = new ScanSpec();
        ItemCollection<ScanOutcome> items = table.scan(scanSpec);

        List<GetPlayerResponse> responses = new ArrayList<>();

        for (Item item : items) {
            responses.add(new GetPlayerResponse(
                    item.getLong("id"),
                    item.getString("name"),
                    item.getInt("backNumber")
            ));
        }

        return responses;
    }
}
