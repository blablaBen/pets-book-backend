package APP2018.REST.Model;

import java.util.Date;

public class PostedStatus {
    String id=null;
    String userId;
    String textValue;
    String[] pictures;
    Date date;
    String[] commentId;


    public PostedStatus(String userId, String textValue, String[] pictures, Date date, String[] commentId) {
        this.userId = userId;
        this.textValue = textValue;
        this.pictures = pictures;
        this.date = date;
        this.commentId = commentId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
