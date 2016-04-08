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
	 * The method that handles HTTP requests for live or running channels.
	 */
	@Path("/live")
	public LiveChannelResource readLive() {
		return new LiveChannelResource();
	}
	
	@GET
	public List<Channel> read() {
		return channelService.read();
	}
	
	@GET
	@Path("/offset/{offset}/limit/{limit}")
	public List<Channel> read(@PathParam("offset") int offset, 
			@PathParam("limit") int limit) {
		
		return channelService.read(offset, limit);
	}
	
	@GET
	@Path("/{channelId}")
	public Channel searchById(@PathParam("channelId") String channelId) {
		return channelService.searchById(channelId);
	}
	
	@GET
	@Path("/owner/{ownerId}")
	public List<Channel> searchByOwner(@PathParam("ownerId") int ownerId) {
		return channelService.searchByOwner(ownerId);
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
