package org.cazter.api.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import org.cazter.api.Server;
import org.cazter.api.model.Channel;

/**
 * The class that handles Channel resource requests and response for live or 
 * running resources.
 * @author patzj
 */
@SuppressWarnings("unused")
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LiveChannelResource {

	private final static Logger LOGGER 
			= Logger.getLogger(LiveChannelResource.class.getName());

	/**
	 * The method that handles HTTP GET requests for list of live 
	 * Channel objects.
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
	 * @return List of live Channel object.
	 */
	@GET
	@Path("/{channelId}")
	public Channel read(@PathParam("channelId") String channelId) {
		return Server.getChannels().get(channelId);
	}
	
	/**
	 * The method that handles HTTP GET requests for List of live channels.
	 * @param filterBean - ChannelFilterBean that contains query parameters 
	 * for filtering resource request results.
	 * @return List of live Channel objects.
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
	 * The method that do the processing of HTTP GET requests for list of live 
	 * Channel objects based on offset and limit specified. Returns a response 
	 * with 404 status code if offset if greater than or equal to the total number 
	 * of live Channel objects.
	 * @param offset - index of the first record of the list to be returned.
	 * @param limit - number of records to be returned.
	 * @return List of live Channel objects.
	 */
	private List<Channel> read(int offset, int limit) {
		ArrayList<Channel> channels 
				= new ArrayList<Channel>(Server.getChannels().values());
		
		limit = offset + limit;
		limit = (channels.size() < limit) ? channels.size() : limit;
		
		try {
			if(channels.size() <= offset) {
				throw new NotFoundException();
			}
			
			return channels.subList(offset, limit);
		} catch(IllegalArgumentException exception) {
			throw new NotFoundException(exception);
		} catch(IndexOutOfBoundsException exception) {
			throw new WebApplicationException(exception);
		}
	}
	
	/**
	 * The method that do the processing of HTTP GET requests for list of live 
	 * Channel objects based on the id of the owner specified. Returns a Response 
	 * with 404 status code if the Channel doesn't exists.
	 * @param owner - Id of the Channel owner.
	 * @return List of live Channel objects.
	 */
	private List<Channel> read(int owner) {
		ArrayList<Channel> channels = new ArrayList<Channel>();
		Iterator<Channel> iterator = Server.getChannels().values().iterator();
		
		if(iterator.hasNext()) {
			Channel channel = iterator.next();
			
			if(channel.getOwnerId() == owner) {
				channels.add(channel);
			}
		}
		
		if(channels.size() < 1) {
			throw new NotFoundException();
		}
		
		return channels;
	}
}
