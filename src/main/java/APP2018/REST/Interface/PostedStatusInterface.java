package APP2018.REST.Interface;

import APP2018.REST.Model.Driver;
import APP2018.REST.Model.PostedStatus;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONArray;
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

    public PostedStatus getOne(String id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));
        Document item = collection.find(query).first();

        if (item == null) {
            return  null;
        }

        PostedStatus status = buildPostedStatusItem(item);
        return status;
    }

    private PostedStatus buildPostedStatusItem(Document item) {
        List<Document> pictures = (List<Document>)item.get("pictures");
        List<String> picturesList = new ArrayList();
        for(Document pic : pictures) {
            picturesList.add(pic.getString("url"));
        }

        PostedStatus status = new PostedStatus(item.getString("userId"),
                item.getString("textValue"),
                picturesList,
                item.getString("date"));

        status.setId(item.getObjectId("_id").toString());
        return status;
    }

    public ArrayList<PostedStatus> getAll() {

        ArrayList<PostedStatus> postList = new ArrayList<PostedStatus>();

        FindIterable<Document> results = collection.find();
        if (results == null) {
            return  postList;
        }
        for (Document item : results) {
            List<Document> pictures = (List<Document>)item.get("pictures");
            List<String> picturesList = new ArrayList();
            for(Document pic : pictures) {
                picturesList.add(pic.getString("url"));
            }

            PostedStatus status = new PostedStatus(item.getString("userId"),
                    item.getString("textValue"),
                    picturesList,
                    item.getString("date"));

            status.setId(item.getObjectId("_id").toString());
            postList.add(status);
        }
        return postList;
    }

    public Object create(JSONObject obj) {
        try {
            Document doc = new Document("userId", obj.getString("userId"))
                    .append("textValue", obj.getString("textValue"))
                    .append("date", obj.getString("date"));

            BasicDBList pictures = new BasicDBList();
            JSONArray picturesJsonArray = obj.getJSONArray("pictures");
            for(int i = 0 ; i <picturesJsonArray.length() ; i++) {
                Document item = new Document();
                item.append("url", (picturesJsonArray.get(i)));
                pictures.add(item);
            }
            doc.append("pictures", pictures);
            collection.insertOne(doc);

        } catch(JSONException e) {

        }
        return obj;
    }


    public Object update(String id, JSONObject obj) {
        try {

            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId(id));

            Document doc = new Document();

            if (obj.has("textValue"))
                doc.append("textValue",obj.getString("textValue"));
            if (obj.has("date"))
                doc.append("date",obj.getString("date"));
            if (obj.has("pictures")) {
                BasicDBList pictures = new BasicDBList();
                JSONArray picturesJsonArray = obj.getJSONArray("pictures");
                for(int i = 0 ; i <picturesJsonArray.length() ; i++) {
                    Document item = new Document();
                    item.append("url", (picturesJsonArray.get(i)));
                    pictures.add(item);
                }
                doc.append("pictures", pictures);
            }

            Document set = new Document("$set", doc);
            collection.updateOne(query,set);

        } catch(JSONException e) {
            System.out.println("Failed to create a document");

        }
        return obj;
    }

    public Object delete(String id) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        collection.deleteOne(query);

        return new JSONObject();
    }
}
