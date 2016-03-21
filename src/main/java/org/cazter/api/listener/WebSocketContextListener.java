package org.cazter.api.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebSocketContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) { }

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO add initial channel upon deployment
	}
}
