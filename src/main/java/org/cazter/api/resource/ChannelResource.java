package org.cazter.api.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.cazter.api.model.Channel;
import org.cazter.api.service.ChannelService;

/**
 * The class that handles Channel resource requests and response.
 * @author patzj
 */
@Path("/channels")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChannelResource {

	ChannelService channelService;
	
	/**
	 * ChannelResource object constructor that takes no parameters. This class 
	 * initializes the channelService.
	 */
	public ChannelResource() {
		channelService = new ChannelService();
	}
	
	/**
	 * The method that handles HTTP POST requests for Channel object persistence. 
	 * Returns a Response with 500 status code on object persistence failure.
	 * @param channel - Channel object to be persisted.
	 * @param uriInfo - Contains data of the request URI.
	 * @return HTTP Response.
	 */
	@POST
	public Response create(Channel channel, @Context UriInfo uriInfo) {
		channelService.create(channel);
		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(channel.getId())
				.build();
		return Response.created(uri).build();
	}
	
	/**
	 * The method that do the processing of HTTP GET requests for list of  
	 * persistent Channel objects.
	 * @return List of persistent Channel object.
	 */
	public List<Channel> read() {
		return channelService.read();
	}
	
	/**
	 * The method that handles HTTP requests for live or running channels.
	 */
	@Path("/live")
	public LiveChannelResource readLive() {
		return new LiveChannelResource();
	}
	
	/**
	 * The method that handles HTTP GET requests for List of persistent channels.
	 * @param filterBean - ChannelFilterBean that contains query parameters 
	 * for filtering resource request results.
	 * @return List of persistent Channel objects.
	 */
	@GET
	@Path("/filter")
	public List<Channel> read(@BeanParam ChannelFilterBean filterBean) {
		ArrayList<Channel> channels = new ArrayList<Channel>();
		int offset = filterBean.getOffset();
		int limit = filterBean.getLimit();
		int owner = filterBean.getOwner();
		
		if(offset >= 0 && limit >= 0 && owner == 0) {
			// Invokes the overloaded method with offset and limit parameters.
			channels.addAll(read(offset, limit));
		} else if(owner > 0) {
			// Invokes the overloaded method with owner parameter
			channels.addAll(read(owner));
		} else {
			throw new WebApplicationException();
		}
		
		return channels;
	}
	
	/**
	 * The method that do the processing of HTTP GET requests for specific list 
	 * of persistent Channel objects based on offset and limit specified. Returns 
	 * a response with 404 status code if offset if greater than or equal to the 
	 * total number of persistent Channel objects.
	 * @param offset - index of the first record of the list to be returned.
	 * @param limit - number of records to be returned.
	 * @return List of persistent Channel objects.
	 */
	private List<Channel> read(int offset, int limit) {
		return channelService.read(offset, limit);
	}
	
	/**
	 * The method that do the processing of HTTP GET requests for specific list 
	 * of persistent Channel objects based on the id of the owner specified. Returns 
	 * a Response with 404 status code if the Channel doesn't exists.
	 * @param owner - Id of the Channel owner.
	 * @return List of persistent Channel objects.
	 */
	private List<Channel> read(int owner) {
		return channelService.searchByOwner(owner);
	}

	/**
	 * The method that handles HTTP GET requests for specific channel. Returned 
	 * result is based on the channelId specified in the URI.
	 * @param channelId - Id of the Channel object to be retrieved from the 
	 * database.
	 * @return List of persistent Channel object.
	 */
	@GET
	@Path("/{channelId}")
	public Channel searchById(@PathParam("channelId") String channelId) {
		return channelService.searchById(channelId);
	}
	
	@PUT
	@Path("/{channelId}")
	public Response update(@PathParam("channelId") String channelId, 
			@Context UriInfo uriInfo, Channel channel) {
		
		channel.setId(channelId);
		channelService.update(channel);
		URI uri = uriInfo.getAbsolutePath();
		return Response.created(uri).build();
	}
	
	@DELETE
	@Path("/{channelId}")
	public Response delete(@PathParam("channelId") String channelId) {
		channelService.delete(channelId);
		return Response.ok().build();
	}
	
}
