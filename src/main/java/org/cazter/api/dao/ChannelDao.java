package org.cazter.api.dao;

import java.util.List;
import org.cazter.api.model.Channel;

/**
 * Channel data access object;
 * @author patzj
 */
public interface ChannelDao {

	public void create(Channel channel);
	public List<Channel> read();
	public int update(Channel channel);
	public int delete(String channelId);
	public Channel searchById(String channelId);
	public List<Channel> searchByOwner(int ownerId);
}
