package APP2018.REST.Model;

import java.util.Date;
import java.util.List;

public class PostedStatus {
    String id=null;
    String userId;
    String textValue;
    List<String> pictures;
    String date;
    int commentCount;


    public PostedStatus(String userId, String textValue, List<String> pictures, String date, int commentCount) {
        this.userId = userId;
        this.textValue = textValue;
        this.pictures = pictures;
        this.date = date;
        this.commentCount = commentCount;
    }

    public void setId(String id) {
        this.id = id;
    }
}
