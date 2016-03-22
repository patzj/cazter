package org.cazter.api.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.cazter.api.Server;
import org.cazter.api.config.ServerConfigurator;
import org.cazter.api.model.Channel;

public class WebSocketContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) { }

	@Override
	public void contextInitialized(ServletContextEvent event) {
		Channel channel = new Channel();
		channel.setId(1);
		channel.setOrigin("file://");
		
		ServerConfigurator.initOriginHeaders();
		ServerConfigurator.getOriginHeaders().add(channel.getOrigin());
		Server.initChannels();
		Server.getChannels().put(channel.getId(), channel);
	}
}
