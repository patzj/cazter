package org.cazter.api.service;

import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import org.cazter.api.dao.ChannelDao;
import org.cazter.api.dao.ChannelDaoImpl;
import org.cazter.api.model.Channel;

public class ChannelService {

	private ChannelDao channelDao;
	
	public ChannelService() {
		channelDao = new ChannelDaoImpl();
	}
	
	public Channel create(Channel channel) {
		Channel newChannel = channelDao.create(channel);
		
		if(newChannel == null) {
			throw new WebApplicationException();
		}
		
		return newChannel;
	}
	
	public List<Channel> read() {
		List<Channel> channels = channelDao.read();
		
		if(channels == null || channels.size() < 1) {
			throw new NotFoundException();
		}
		
		return channels;
	}
	
	public List<Channel> read(int offset, int limit) {
		List<Channel> channels = channelDao.read(offset, limit);
		
		if(channels == null || channels.size() < 1) {
			throw new NotFoundException();
		}
		
		return channels;
	}
	
	public int update(Channel channel) {
		int affectedRows = channelDao.update(channel);
		
		if(affectedRows < 1) {
			throw new WebApplicationException();
		}
		
		return affectedRows;
	}
	
	public int delete(String channelId) {
		int affectedRows = channelDao.delete(channelId);
		
		if(affectedRows < 1) {
			throw new WebApplicationException();
		}
		
		return affectedRows;
	}
	
	public Channel searchById(String channelId) {
		Channel channel = channelDao.searchById(channelId);
		
		if(channel == null) {
			throw new NotFoundException();
		}
		
		return channel;
	}
	
	public List<Channel> searchByOwner(int ownerId) {
		List<Channel> channels = channelDao.searchByOwner(ownerId);
		
		if(channels == null || channels.size() < 1) {
			throw new NotFoundException();
		}
		
		return channels;
	}
}
