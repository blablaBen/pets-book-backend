package APP2018.REST.Interface;

import APP2018.REST.Model.Driver;
import APP2018.REST.Model.PostedStatus;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PostedStatusInterface {
    MongoCollection<Document> collection;

    public PostedStatusInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("APP18_Workshop4");

        this.collection = database.getCollection("postedstatus");
    }

    public ArrayList<PostedStatus> getAll() {

        ArrayList<PostedStatus> postList = new ArrayList<PostedStatus>();

        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  postList;
        }
        for (Document item : results) {
            List<Document> pictures = (List<Document>)item.get("picture");
            List<String> picturesList = pictures.stream().map(d -> d.toString()).collect(Collectors.toList());

            List<Document> commentId = (List<Document>)item.get("commentId");
            List<String> commentIdList = commentId.stream().map(d -> d.toString()).collect(Collectors.toList());

            PostedStatus status = new PostedStatus(item.getString("userId"),
                    item.getString("textValue"),
                    picturesList,
                    item.getDate("date"),
                    commentIdList);

            status.setId(item.getObjectId("_id").toString());
            postList.add(status);
        }
        return postList;
    }

    public Object create(JSONObject obj) {
        try {
            Document doc = new Document("userId", obj.getString("userId"))
                    .append("textValue", obj.getString("textValue"))
                    .append("date", obj.getString("date"))
                    .append("pictures", obj.getJSONArray("pictures"))
                    .append("commentId", obj.getJSONArray("commentId"));
            collection.insertOne(doc);

        } catch(JSONException e) {

        }
        return obj;
    }
}
