package org.cazter.api.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionPool {

	private static SessionPool sessionPoll = null;
	private static SessionFactory sessionFactory;
	
	private void newSessionFactory() {
		Configuration configuration = new Configuration()
				.configure()
				.setProperty("hibernate.connection.url", "jdbc:mysql://" 
						+ System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":"
						+ System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/"
						+ System.getenv("OPENSHIFT_APP_NAME"))
				.setProperty("hibernate.connection.username", 
						System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"))
				.setProperty("hibernate.connection.password", 
						System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
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
