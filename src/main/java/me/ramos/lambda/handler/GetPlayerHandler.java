package me.ramos.lambda.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import java.util.Iterator;
import me.ramos.lambda.config.AWSConfig;
import me.ramos.lambda.request.GetPlayerRequest;
import me.ramos.lambda.response.GetPlayerResponse;

public class GetPlayerHandler implements RequestHandler<GetPlayerRequest, GetPlayerResponse> {

    @Override
    public GetPlayerResponse handleRequest(GetPlayerRequest request, Context context) {
        Long id = request.getId();
        AmazonDynamoDB amazonDynamoDB = AWSConfig.initDynamoDbClient();
        DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);
        Table table = dynamoDB.getTable(AWSConfig.getDynamodbTableName());

        QuerySpec spec = new QuerySpec().withKeyConditionExpression("id = :v_id")
                .withValueMap(new ValueMap().withLong(":v_id", id));

        ItemCollection<QueryOutcome> items = table.query(spec);

        Iterator<Item> iterator = items.iterator();
        Item item = null;
        GetPlayerResponse response = null;
        while (iterator.hasNext()) {
            item = iterator.next();
            System.out.println(item.toJSONPretty());
            response = new GetPlayerResponse(item.getLong("id"), item.getString("name"), item.getInt("backNumber"));
        }

        return response;
    }
}
