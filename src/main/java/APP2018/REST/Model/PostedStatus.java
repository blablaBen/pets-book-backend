package APP2018.REST.Model;

import java.util.Date;
import java.util.List;

public class PostedStatus {
    String id=null;
    String userId;
    String textValue;
    List<String> pictures;
    String date;


    public PostedStatus(String userId, String textValue, List<String> pictures, String date) {
        this.userId = userId;
        this.textValue = textValue;
        this.pictures = pictures;
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }
}
