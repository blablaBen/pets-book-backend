package APP2018.REST.Model;

import java.util.Date;

public class PostComment {
    String id=null;
    String postId;
    String content;
    String time;
    String userId;


    public PostComment(String postId, String content, String time, String userId) {
        this.postId = postId;
        this.content = content;
        this.time = time;
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
