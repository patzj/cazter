package org.cazter.api.dao;

import java.util.List;
import org.cazter.api.model.Channel;

/**
 * Channel data access object;
 * @author patzj
 */
public interface ChannelDao {

	public int create(Channel channel);
	public List<Channel> read();
	public int update(Channel channel);
	public int delete(String channelId);
	public Channel search(Channel channel);
	public Channel searchById(String channelId);
	public Channel searchByOwner(int ownerId);
}
