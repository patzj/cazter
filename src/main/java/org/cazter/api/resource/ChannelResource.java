package org.cazter.api.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.websocket.Session;
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
import org.cazter.api.Server;
import org.cazter.api.model.Channel;
import org.cazter.api.service.ChannelService;

@Path("/channels")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChannelResource {

	ChannelService channelService;
	
	public ChannelResource() {
		channelService = new ChannelService();
	}
	
	@POST
	public Response create(Channel channel, @Context UriInfo uriInfo) {
		channelService.create(channel);
		URI uri = uriInfo.getAbsolutePathBuilder()
				.path(channel.getId())
				.build();
		return Response.created(uri).build();
	}
	
	@GET
	@Path("/live")
	public List<Channel> readLive() {
		return new ArrayList<Channel>(Server.getChannels().values());
	}
	
	@GET
	@Path("/live/{channelId}/users")
	public List<String> readLive(@PathParam("channelId") String channelId) {
		Channel channel = Server.getChannels().get(channelId);
		Map<String, Session> sessions = channel.getSessions();
		Iterator<Entry<String, Session>> iterator 
				= sessions.entrySet().iterator();
		Session session;
		String userId;
		List<String> users = new ArrayList<String>();
		
		while(iterator.hasNext()) {
			session = iterator.next().getValue();
			userId = (String) session.getUserProperties().get("userId");
			users.add(userId);
		}
		
		return users;
	}
	
	@GET
	@Path("/live/{channelId}")
	public Channel searchByIdLive(@PathParam("channelId") String channelId) {
		
		return Server.getChannels().get(channelId);
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
