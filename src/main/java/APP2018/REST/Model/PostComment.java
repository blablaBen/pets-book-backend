package APP2018.REST.Model;

import java.util.Date;

public class PostComment {
    String id=null;
    String commentId;
    String content;
    Date time;
    String userId;


    public PostComment(String commentId, String content, Date time, String userId) {
        this.commentId = commentId;
        this.content = content;
        this.time = time;
        this.userId = userId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
