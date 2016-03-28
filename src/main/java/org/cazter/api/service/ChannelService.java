package org.cazter.api.service;

import java.util.List;
import org.cazter.api.dao.ChannelDao;
import org.cazter.api.dao.ChannelDaoImpl;
import org.cazter.api.model.Channel;

public class ChannelService {

	private ChannelDao channelDao;
	
	public ChannelService() {
		channelDao = new ChannelDaoImpl();
	}
	
	public void create(Channel channel) {
		channelDao.create(channel);
	}
	
	public List<Channel> read() {
		return channelDao.read();
	}
	
	public List<Channel> read(int offset, int limit) {
		return channelDao.read(offset, limit);
	}
	
	public int update(Channel channel) {
		return channelDao.update(channel);
	}
	
	public int delete(String channelId) {
		return channelDao.delete(channelId);
	}
	
	public Channel searchById(String channelId) {
		return channelDao.searchById(channelId);
	}
	
	public List<Channel> searchByOwner(int ownerId) {
		return channelDao.searchByOwner(ownerId);
	}
}
