package org.cazter.api.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import static org.cazter.api.dao.HibernateCfgConstants.*;

/**
 * Container class for SessionFactory object that implements a Singleton 
 * pattern.
 * @author patzj
 */
public class SessionPool {

	private static SessionPool sessionPoll = null;
	private static SessionFactory sessionFactory;
	
	/**
	 * Session object private constructor that takes no arguments. The 
	 * method that initializes the SessionFactory object contained in this 
	 * class.  
	 */
	private void SessionPool() {
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
	
	/**
	 * Static method that returns an instance of SessionPool. If SessionPool 
	 * instance is null, this method creates an instance of SessionPool and 
	 * returns it. This method also instantiates a SessionFactory object.
	 * @return SessionPool object.
	 */
	public static SessionPool getInstance() {
		if(sessionPoll == null) {
			sessionPoll = new SessionPool();
		}
		
		return sessionPoll;
	}
	
	/**
	 * The method that returns the SessionFactory object of the SessionPool 
	 * class.
	 * @return SessionFactory object.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
