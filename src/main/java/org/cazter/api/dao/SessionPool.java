package org.cazter.api.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import static org.cazter.api.dao.HibernateCfgConstants.*;

public class SessionPool {

	private static SessionPool sessionPoll = null;
	private static SessionFactory sessionFactory;
	
	private void newSessionFactory() {
		// Development/offline configuration.
		Configuration configuration = new Configuration()
				.configure()
				.setProperty("hibernate.connection.url", 
						OFFLINE_DB_CONNECTION_URL)
				.setProperty("hibernate.connection.username", 
						OFFLINE_DB_CONNECTION_USERNAME)
				.setProperty("hibernate.connection.password", 
						OFFLINE_DB_CONNECTION_PASSWORD);
		
		// Production/online configuration.
		/*
		Configuration configuration = new Configuration()
				.configure()
				.setProperty("hibernate.connection.url", 
						OFFLINE_DB_CONNECTION_URL)
				.setProperty("hibernate.connection.username", 
						OFFLINE_DB_CONNECTION_USERNAME)
				.setProperty("hibernate.connection.password", 
						OFFLINE_DB_CONNECTION_PASSWORD);
		*/
		
		StandardServiceRegistryBuilder serviceRegistryBuilder 
				= new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionPool getInstance() {
		if(sessionPoll == null) {
			sessionPoll = new SessionPool();
			sessionPoll.newSessionFactory();
		}
		
		return sessionPoll;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
