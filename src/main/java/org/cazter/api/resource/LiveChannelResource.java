package org.cazter.api.resource;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.cazter.api.Server;
import org.cazter.api.model.Channel;

/**
 * The class that handles Channel resource requests and response for live or 
 * running resources.
 * @author patzj
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LiveChannelResource {

	/**
	 * The method that handles HTTP GET requests for List of live channels.
	 * @return List of live Channel objects.
	 */
	@GET
	public List<Channel> read() {
		return new ArrayList<Channel>(Server.getChannels().values());
	}
	
	/**
	 * The method that handles HTTP GET requests for specific channel. Returned 
	 * result is based on the channelId specified in the URI.
	 * @param channelId
	 * @return
	 */
	@GET
	@Path("/{channelId}")
	public Channel read(@PathParam("channelId") String channelId) {
		return Server.getChannels().get(channelId);
	}
}
