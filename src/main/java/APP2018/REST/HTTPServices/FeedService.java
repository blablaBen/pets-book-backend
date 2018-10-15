package APP2018.REST.HTTPServices;

import APP2018.REST.Interface.DriverInterface;
import APP2018.REST.Interface.PostedStatusInterface;
import APP2018.REST.Model.Driver;
import APP2018.REST.Model.PostedStatus;
import APP2018.REST.PATCH;
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
    public ArrayList<PostedStatus> getPosts() {
        ArrayList<PostedStatus> postedStatusList = new ArrayList<PostedStatus>();
        postedStatusList = serviceInterface.getAll();

        return postedStatusList;
    }

    //GET Post - Single
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public PostedStatus getPost(@PathParam("id") String id) {
        return serviceInterface.getOne(id);

    }

    //POST a Post
    @POST
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object createPost(JSONObject obj) {
        return serviceInterface.create(obj);
    }

    //PATCH a Post
    @PATCH
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object updatePost(@PathParam("id") String id, JSONObject obj) {
        return serviceInterface.update(id,obj);

    }

    //DELETE a Driver
    @DELETE
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Object deletePost(@PathParam("id") String id) {

        return serviceInterface.delete(id);

    }
}
