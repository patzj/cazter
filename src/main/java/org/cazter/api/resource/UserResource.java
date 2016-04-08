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
 * Class that handles User resource requests and response.
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
	
	/**
	 * The method that handles HTTP POST requests for User object persistence. 
	 * Returns a Response with 500 status code on object persistence failure.
	 * @param user - User object to be persisted.
	 * @param uriInfo - Contains data of the request URI.
	 * @return HTTP Response
	 */
	@POST
	public Response create(User user, @Context UriInfo uriInfo) {
		userService.create(user);
		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(String.valueOf(user.getUserId()))
				.build();
		return Response.created(uri).build();
	}
	
	/**
	 * The method that handles HTTP GET requests for List of persistent User 
	 * objects. Returns a response with 404 status code if no persistent User 
	 * object exists.
	 * @return List of User objects.
	 */
	@GET
	public List<User> read() {
		return userService.read();
	}
	
	/**
	 * The method that handles HTTP GET requests for List of persistent User 
	 * objects. Returned results is based on the offset and limit specified 
	 * in the URI. Returns a response owith 404 status code if the offset is 
	 * greater than the total number of persistent User objects.
	 * @param offset - index of the first record of the list to be returned.
	 * @param limit - number of records to be returned.
	 * @return List of User objects.
	 */
	@GET
	@Path("/offset/{offset}/limit/{limit}")
	public List<User> read(@PathParam("offset") int offset, 
			@PathParam("limit") int limit) {
		
		return userService.read(offset, limit);
	}
	
	/**
	 * The method that handles HTTP GET requests for specific user. Returned 
	 * results is based on the userId specified in the URI. Returns a Response 
	 * with 404 status code if the User doesn't exist.
	 * @param userId - Id of the User to be returned.
	 * @return Persistent User object.
	 */
	@GET
	@Path("/{userId}")
	public User searchByUserId(@PathParam("userId") int userId) {
		return userService.searchByUserId(userId);
	}
	
	/**
	 * The method that handles HTTP GET requests for specific user. Returned 
	 * result is based on the username specified in the URI. Returns a Reponse 
	 * with 404 status code if the User doesn't exists.
	 * @param username - Username of the User to be returned.
	 * @return Persistent User object.
	 */
	@GET
	@Path("/username/{username}")
	public User searchByUsername(@PathParam("username") String username) {
		return userService.searchByUsername(username);
	}
	
	/**
	 * The method that handles HTTP GET requests for specific user. Returned 
	 * result is base on the email address specified in the URI. Returns a 
	 * Response with 404 status code if the User doesn't exists.
	 * @param emailAddress - Email address of the User to be returned.
	 * @return Persistent User object.
	 */
	@GET
	@Path("/email/{emailAddress}")
	public User searchByEmailAddress(@PathParam("emailAddress") String emailAddress) {
		return userService.searchByEmailAddress(emailAddress);
	}
	
	/**
	 * The method that handles HTTP PUT requests for updates of persistent User 
	 * objects. Returns a Response with 500 status code on persistent object 
	 * update failure.
	 * @param userId - Id of the user to be updated.
	 * @param uriInfo - Contains data of the request URI.
	 * @param user - User object with updated data.
	 * @return HTTP Response.
	 */
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
	
	/**
	 * The method that handles HTTP DELETE requests for deletion of persistent 
	 * User objects. Returns a Response with 500 status code on persistent 
	 * object deletion failure.
	 * @param userId - Id of the persistent User object to be deleted.
	 * @return HTTP Response.
	 */
	@DELETE
	@Path("/{userId}")
	public Response delete(@PathParam("userId") int userId) {
		userService.delete(userId);
		
		// 204 No Content
		return Response.noContent().build();
	}
}
