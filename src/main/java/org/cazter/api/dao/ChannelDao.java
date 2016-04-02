package org.cazter.api.dao;

import java.util.List;
import org.cazter.api.model.Channel;

/**
 * Channel data access object;
 * @author patzj
 */
public interface ChannelDao {

	public Channel create(Channel channel);
	public List<Channel> read();
	public List<Channel> read(int offset, int limit);
	public int update(Channel channel);
	public int delete(String channelId);
	public Channel searchById(String channelId);
	public List<Channel> searchByOwner(int ownerId);
}
