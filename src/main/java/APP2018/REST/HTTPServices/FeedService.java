package APP2018.REST.HTTPServices;

import APP2018.REST.Interface.DriverInterface;
import APP2018.REST.Interface.PostedStatusInterface;
import APP2018.REST.Model.Driver;
import APP2018.REST.Model.PostedStatus;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("newFeeds")
public class FeedService {

    PostedStatusInterface serviceInterface = new PostedStatusInterface();

    //GET Posts - All
    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<PostedStatus> getDrivers() {
        ArrayList<PostedStatus> postedStatusList = new ArrayList<PostedStatus>();
        postedStatusList = serviceInterface.getAll();

        return postedStatusList;
    }

    //POST a Post
    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object createPost(JSONObject obj) {
        return serviceInterface.create(obj);
    }
}
