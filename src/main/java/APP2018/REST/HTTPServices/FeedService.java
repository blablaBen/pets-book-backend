package APP2018.REST.HTTPServices;

import APP2018.REST.Interface.DriverInterface;
import APP2018.REST.Interface.PostedStatusInterface;
import APP2018.REST.Model.Driver;
import APP2018.REST.Model.PostComment;
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

    //DELETE a Post
    @DELETE
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Object deletePost(@PathParam("id") String id) {
        return serviceInterface.delete(id);
    }

    //GET Comments - All
    @GET
    @Path("{postId}/comments")
    @Produces({ MediaType.APPLICATION_JSON})
    public ArrayList<PostComment> getComments(@PathParam("postId") String postId) {
        return serviceInterface.getAllComments(postId);
    }

    //Add Comments - All
    @POST
    @Path("{postId}/comments")
    @Consumes({ MediaType.APPLICATION_JSON})
    @Produces({ MediaType.APPLICATION_JSON})
    public Object postComments(JSONObject obj, @PathParam("postId") String postId) {
        return serviceInterface.createComment(postId, obj);
    }

    //DELETE a Comment
    @DELETE
    @Path("{postId}/comments/{commentId}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Object deleteComment(@PathParam("commentId") String commentId) {
        return serviceInterface.deleteComment(commentId);
    }
}
