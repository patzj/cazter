package org.cazter.api.dao;

import java.util.List;
import org.cazter.api.model.Channel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ChannelDaoImpl implements ChannelDao {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	public ChannelDaoImpl() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder 
				= new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	@Override
	public int create(Channel channel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Channel> read() {
		startSession();
		Query query = session.createQuery("from Channel");
		@SuppressWarnings("unchecked")
		List<Channel> channels = query.list();
		endSession();
		
		return channels;
	}

	@Override
	public int update(Channel channel) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String channelId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Channel search(Channel channel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Channel searchById(String channelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Channel searchByOwner(int ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	private void endSession() {
		transaction.commit();
		session.flush();
		session.close();
	}
}
