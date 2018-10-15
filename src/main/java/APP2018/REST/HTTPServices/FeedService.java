package APP2018.REST.HTTPServices;

import APP2018.REST.Interface.DriverInterface;
import APP2018.REST.Interface.PostedStatusInterface;
import APP2018.REST.Model.Driver;
import APP2018.REST.Model.PostedStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
