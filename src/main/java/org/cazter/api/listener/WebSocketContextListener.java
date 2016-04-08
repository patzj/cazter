package org.cazter.api.listener;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.cazter.api.Server;
import org.cazter.api.config.ServerConfigurator;
import org.cazter.api.model.Channel;
import org.cazter.api.service.ChannelService;


public class WebSocketContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) { }

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServerConfigurator.initOriginHeaders();
		Server.initChannels();
		
		ChannelService channelService = new ChannelService();
		List<Channel> channels = channelService.read();
		
		for(Channel channel : channels) {
			Server.getChannels().put(channel.getId(), channel);
			ServerConfigurator.getOriginHeaders().add(channel.getOrigin());
		}
		
		/*
		Channel channel = new Channel();
		channel.setId("1");
		channel.setOwnerId(1);
		channel.setOrigin("file://");
		Server.getChannels().put(channel.getId(), channel);
		ServerConfigurator.getOriginHeaders().add(channel.getOrigin());
		*/
	}
}
