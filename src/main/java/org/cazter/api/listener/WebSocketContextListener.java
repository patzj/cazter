package org.cazter.api.listener;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.cazter.api.Server;
import org.cazter.api.config.ServerConfigurator;
import org.cazter.api.dao.ChannelDaoImpl;
import org.cazter.api.model.Channel;

public class WebSocketContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) { }

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServerConfigurator.initOriginHeaders();
		Server.initChannels();
		
		ChannelDaoImpl channelDao = new ChannelDaoImpl();
		List<Channel> channels = channelDao.read();
		
		for(Channel channel : channels) {
			Server.getChannels().put(channel.getId(), channel);
			ServerConfigurator.getOriginHeaders().add(channel.getOrigin());
		}
	}
}
