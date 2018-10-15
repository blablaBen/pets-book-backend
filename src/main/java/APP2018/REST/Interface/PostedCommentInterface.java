package APP2018.REST.Interface;

import APP2018.REST.Model.PostComment;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostedCommentInterface {
    MongoCollection<Document> collection;

    public PostedCommentInterface() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("APP18_Workshop4");

        this.collection = database.getCollection("postedcomemnt");
    }


    public Object create(String postId, JSONObject obj) {
        try {
            Document doc = new Document("postId", postId)
                    .append("content", obj.getString("content"))
                    .append("time", obj.getString("time"))
                    .append("userId", obj.getString("userId"));

            collection.insertOne(doc);

        } catch(JSONException e) {

        }
        return obj;
    }

    public Object delete(String commentId) {
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(commentId));

        collection.deleteOne(query);

        return new JSONObject();
    }

    public List<PostComment> getAll(String postId) {

        BasicDBObject query = new BasicDBObject();
        query.put("postId", new ObjectId(postId));

        FindIterable<Document> items = collection.find(query);
        List<PostComment> result = new ArrayList();
        for(Document item : items) {
            PostComment post = new PostComment(item.getString("postId"), item.getString("content"), item.getString("time"), item.getString("userId"));
            post.setId(item.getString("_id"));
            result.add(post);
        }

        return result;
    }
}
