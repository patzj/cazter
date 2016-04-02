package org.cazter.api.dao;

import java.util.List;
import org.cazter.api.model.Channel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Implementation class of the ChannelDao interface.
 * @author patzj
 */
public class ChannelDaoImpl implements ChannelDao {

	private static SessionPool sessionPool;
	private static SessionFactory sessionFactory;
	
	/**
	 * ChannelDaoImpl object constructor that takes no parameters. The 
	 * method that initializes the SessionFactory object.
	 */
	public ChannelDaoImpl() {
		sessionPool = SessionPool.getInstance();
		sessionFactory = sessionPool.getSessionFactory();
	}
	
	/**
	 * The method that persists Channel object data to the database.
	 * @param channel - Channel object to be persisted.
	 */
	@Override
	public void create(Channel channel) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(channel);
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
		} finally {
			session.close();
		}
	}

	/**
	 * The method that retrieves all the persisted Database objects from the 
	 * database.
	 * @return List of Channel objects from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> read() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Channel> channels = null;
		
		try {
			transaction = session.getTransaction();
			channels = session.createQuery("FROM Channel").list();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
		} finally {
			session.close();
		}
		
		return channels;
	}
	
	/**
	 * The method that retrieves a list of persisted Channel objects from the 
	 * database depending on offset and limit specified.
	 * @param offset - index of the first record of the list to be returned.
	 * @param limit - number of records to be returned.
	 * @return List of Channel objects from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> read(int offset, int limit) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Channel> channels = null;
		
		try {
			transaction = session.getTransaction();
			channels = session.createQuery("FROM Channel")
					.setFirstResult(offset)
					.setMaxResults(limit)
					.list();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
		} finally {
			session.close();
		}
		
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
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int affectedRows = 0;;
		
		try {
			transaction = session.beginTransaction();
			affectedRows = session.createQuery("UPDATE Channel SET "
					+ "origin = :origin WHERE id = :id")
					.setString("origin", channel.getOrigin())
					.setString("id", channel.getId())
					.executeUpdate();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
		} finally {
			session.close();
		}

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
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int affectedRows = 0;
		
		try {
			transaction = session.beginTransaction();
			affectedRows = session
					.createQuery("DELETE FROM Channel WHERE id = :id")
					.setString("id", channelId)
					.executeUpdate();
			transaction.commit();
		} catch(HibernateException e) {
			rollbackTx(transaction);
		} finally {
			session.close();
		}
		
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
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Channel> channels = null;
		
		try {
			transaction = session.beginTransaction();
			channels = session.createQuery("FROM Channel WHERE "
					+ "id = :id").setString("id", channelId).list();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
		} finally {
			session.close();
		}
		
		return (channels.size() > 0) ? channels.get(0) : null;
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
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Channel> channels = null;
		
		try {
			transaction = session.beginTransaction();
			channels = session.createQuery("FROM Channnel WHERE "
					+ "ownerId = :ownerId")
					.setInteger("ownerId", ownerId)
					.list();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
		} finally {
			session.close();
		}
		
		return channels;
	}
	
	private void rollbackTx(Transaction transaction) {
		if(transaction != null) {
			transaction.rollback();
		}
	}
}
