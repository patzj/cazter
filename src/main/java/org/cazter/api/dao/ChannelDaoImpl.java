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

/**
 * Implementation class of the ChannelDao interface.
 * @author patzj
 */
public class ChannelDaoImpl implements ChannelDao {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	/**
	 * ChannelDaoImpl object constructor that takes no parameters. The 
	 * method that initializes the SessionFactory object.
	 */
	public ChannelDaoImpl() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder 
				= new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	/**
	 * The method that persists Channel object data to the database.
	 * @param channel - Channel object to be persisted.
	 */
	@Override
	public void create(Channel channel) {
		startSession();
		session.save(channel);
		endSession();
	}

	/**
	 * The method that retrieves all the persisted Database objects from the 
	 * database.
	 * @return List of Channel objects from the database.
	 */
	@Override
	public List<Channel> read() {
		startSession();
		Query query = session.createQuery("FROM Channel");
		@SuppressWarnings("unchecked")
		List<Channel> channels = query.list();
		endSession();
		
		return channels;
	}

	/**
	 * The method that updates a specific persisted Channel object. 
	 * @param channel - Channel object to be updated.
	 * @return int value that represents the affected database rows after 
	 * the update.
	 */
	@Override
	public int update(Channel channel) {
		int affectedRows;
		
		startSession();
		Query query = session.createQuery("UPDATE Channel SET "
				+ "origin = :origin WHERE id = :id");
		query.setString("origin", channel.getOrigin());
		query.setString("id", channel.getId());
		affectedRows = query.executeUpdate();
		endSession();
		
		return affectedRows;
	}
	
	/**
	 * The method that deletes a specific persisted Channel object.
	 * @param channelId - Id of the Channel object to be deleted from the 
	 * database.
	 * @return int value that represent the affected database rows after 
	 * the delete.
	 */
	@Override
	public int delete(String channelId) {
		int affectedRows;
		
		startSession();
		Query query = session.createQuery("DELETE FROM Channel WHERE "
				+ "id = :id");
		query.setString("id", channelId);
		affectedRows = query.executeUpdate();
		endSession();
		
		return affectedRows;
		
	}

	/**
	 * The method that retrieves a specific persisted Channel object.
	 * @param channelId - Id of the Channel object to be retrieved from the 
	 * database.
	 * @return Channel object from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Channel searchById(String channelId) {
		List<Channel> channels;
		
		startSession();
		Query query = session.createQuery("FROM Channel WHERE "
				+ "id = :id");
		query.setString("id", channelId);
		channels = query.list();
		endSession();
		
		return channels.get(0);
	}

	/**
	 * The method that retrieves a list of persisted Channel objects based 
	 * on the Id of the owner.
	 * @param owerId - If of the Channel owner
	 * @return List of Channels owned by the specified owner.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> searchByOwner(int ownerId) {
		List<Channel> channels;
		
		startSession();
		Query query = session.createQuery("FROM Channnel WHERE "
				+ "ownerId = :ownerId");
		query.setInteger("ownerId", ownerId);
		channels = query.list();
		endSession();
		
		return channels;
	}

	/**
	 * The method that opens a Session object and begins a Transaction.
	 */
	private void startSession() {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	/**
	 * The method that commits query to the Transaction and closes the 
	 * Session object.
	 */
	private void endSession() {
		transaction.commit();
		session.flush();
		session.close();
	}
}
