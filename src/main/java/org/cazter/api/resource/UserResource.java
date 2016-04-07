package org.cazter.api.resource;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.cazter.api.model.User;
import org.cazter.api.service.UserService;

/**
 * Resource class that handles User resource requests from clients.
 * @author patzj
 */
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private UserService userService;
	
	/**
	 * UserResource object constructor that takes no parameters. This class 
	 * initializes the UserSerice.
	 */
	public UserResource() {
		userService = new UserService();
	}
	
	@POST
	public Response create(User user, @Context UriInfo uriInfo) {
		userService.create(user);
		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(String.valueOf(user.getUserId()))
				.build();
		return Response.created(uri).build();
	}
	
	@GET
	public List<User> read() {
		return userService.read();
	}
	
	@GET
	@Path("/offset/{offset}/limit/{limit}")
	public List<User> read(@PathParam("offset") int offset, 
			@PathParam("limit") int limit) {
		
		return userService.read(offset, limit);
	}
	
	@GET
	@Path("/{userId}")
	public User searchByUserId(@PathParam("userId") int userId) {
		return userService.searchByUserId(userId);
	}
	
	@GET
	@Path("/username/{username}")
	public User searchByUsername(@PathParam("username") String username) {
		return userService.searchByUsername(username);
	}
	
	@GET
	@Path("/email/{emailAddress}")
	public User searchByEmailAddress(@PathParam("emailAddress") String emailAddress) {
		return userService.searchByEmailAddress(emailAddress);
	}
	
	@PUT
	@Path("/{userId}")
	public Response update(@PathParam("userId") int userId, 
			@Context UriInfo uriInfo, User user) {
		
		user.setUserId(userId);
		userService.update(user);
		URI uri = uriInfo.getAbsolutePath();
		
		// 204 No Content
		return Response
				.status(Status.NO_CONTENT)
				.header("Location", uri)
				.build();
	}
	
	@DELETE
	@Path("/{userId}")
	public Response delete(@PathParam("userId") int userId) {
		userService.delete(userId);
		
		// 204 No Content
		return Response.noContent().build();
	}
}
