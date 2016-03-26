package org.cazter.api.listener;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.cazter.api.Server;
import org.cazter.api.config.ServerConfigurator;
import org.cazter.api.model.Channel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class WebSocketContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) { }

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServerConfigurator.initOriginHeaders();
		Server.initChannels();
		
		SessionFactory sessionFactory 
				= new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Channel");
		@SuppressWarnings("unchecked")
		List<Channel> channels = query.list();
		session.getTransaction().commit();
		session.close();
		
		for(Channel channel : channels) {
			Server.getChannels().put(channel.getId(), channel);
			ServerConfigurator.getOriginHeaders().add(channel.getOrigin());
		}
	}
}
