package org.cazter.api.service;

import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import org.cazter.api.dao.ChannelDao;
import org.cazter.api.dao.ChannelDaoImpl;
import org.cazter.api.model.Channel;

/**
 * The middle class between the ChannelResource and other clients, and the 
 * data access object.
 * @author patzj
 */
public class ChannelService {

	private ChannelDao channelDao;
	
	/**
	 * ChannelService object constructor that takes no arguments. The method 
	 * that instantiates the ChannelDao.
	 */
	public ChannelService() {
		channelDao = new ChannelDaoImpl();
	}
	
	/**
	 * The service method that routes the Channel object to be persisted.
	 * @param channel - Channel object to be persisted.
	 * @return Persisted Channel object.
	 */
	public Channel create(Channel channel) {
		Channel newChannel = channelDao.create(channel);
		
		if(newChannel == null) {
			throw new WebApplicationException();
		}
		
		return newChannel;
	}
	
	/**
	 * The service method that retrieves the list of persistent Channel objects.
	 * @return List of Channel objects from the database.
	 */
	public List<Channel> read() {
		List<Channel> channels = channelDao.read();
		
		if(channels == null || channels.size() < 1) {
			throw new NotFoundException();
		}
		
		return channels;
	}
	
	/**
	 * The service method that retrieves a list of persistent Channel objects 
	 * depending on offset and limit specified.
	 * @param offset - index of the first record of the list to be returned.
	 * @param limit - number of records to be returned.
	 * @return List of Channel objects from the database.
	 */
	public List<Channel> read(int offset, int limit) {
		List<Channel> channels = channelDao.read(offset, limit);
		
		if(channels == null || channels.size() < 1) {
			throw new NotFoundException();
		}
		
		return channels;
	}
	
	/**
	 * The service method that routes the updates of the persistent Channel 
	 * objects.
	 * @param channel - Channel object to be updated.
	 * @return int value that represents the affected database rows after 
	 * the update.
	 */
	public int update(Channel channel) {
		int affectedRows = channelDao.update(channel);
		
		if(affectedRows < 1) {
			throw new WebApplicationException();
		}
		
		return affectedRows;
	}
	
	/**
	 * The service method that routes the id of a specific persistent Channel 
	 * object to be deleted.
	 * @param channelId - Id of the Channel object to be deleted from the 
	 * database.
	 * @return int value that represent the affected database rows after 
	 * the delete.
	 */
	public int delete(String channelId) {
		int affectedRows = channelDao.delete(channelId);
		
		if(affectedRows < 1) {
			throw new WebApplicationException();
		}
		
		return affectedRows;
	}
	
	/**
	 * The service method that retrieves a specific persisted Channel object.
	 * @param channelId - Id of the Channel object to be retrieved from the 
	 * database.
	 * @return Channel object from the database.
	 */
	public Channel searchById(String channelId) {
		Channel channel = channelDao.searchById(channelId);
		
		if(channel == null) {
			throw new NotFoundException();
		}
		
		return channel;
	}
	
	/**
	 * The service method that retrieves a list of persisted Channel objects based 
	 * on the Id of the owner.
	 * @param owerId - If of the Channel owner
	 * @return List of Channels owned by the specified owner.
	 */
	public List<Channel> searchByOwner(int ownerId) {
		List<Channel> channels = channelDao.searchByOwner(ownerId);
		
		if(channels == null || channels.size() < 1) {
			throw new NotFoundException();
		}
		
		return channels;
	}
}
