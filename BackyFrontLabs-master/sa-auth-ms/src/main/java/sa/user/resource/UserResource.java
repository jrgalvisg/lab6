package sa.user.resource;

import sa.user.model.User;
import sa.user.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.net.URI;
import java.util.List;

@Path("/users")
public class UserResource {

    ResponseBuilder response;

    @Context
    UriInfo uriInfo;

    @EJB
    UserService userService;

    @GET
    public List<User> getAllUsers(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return userService.getAllUsers(first, maxResult);
    }

    @GET
    @Path("{id}")
    public Response getUserById(@PathParam("id") int id) {
        User user = userService.getUserById(id);
        response = Response.status(Response.Status.OK);
        response.entity(user);
        return response.build();
    }

    @POST
    public Response createUser(User user) {
        User createdUser = userService.createUser(user);
        response = Response.status(Response.Status.CREATED);
        response.entity(createdUser);
        return response.build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") int id, User user) {
        User updatedUser = userService.updateUser(id, user);
        response = Response.status(Response.Status.OK);
        response.entity(updatedUser);
        return response.build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        int deletedUserId = userService.deleteUser(id);
        response = Response.status(Response.Status.OK);
        response.entity(deletedUserId);
        return response.build();
    }
}
