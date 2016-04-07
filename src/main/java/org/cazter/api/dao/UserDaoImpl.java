package org.cazter.api.dao;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.cazter.api.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Implementation class of UserDao interface.
 * @author patzj
 */
@SuppressWarnings("unused")
public class UserDaoImpl implements UserDao {

	private static SessionPool sessionPool;
	private static SessionFactory sessionFactory;
	private final static Logger LOGGER 
			= Logger.getLogger(UserDaoImpl.class.getName());
			
	
	/**
	 * UserDaoImpl object constructor that takes no parameters. The 
	 * method that initializes the SessionFactory object.
	 */
	public UserDaoImpl() {
		sessionPool = SessionPool.getInstance();
		sessionFactory = sessionPool.getSessionFactory();
	}
	
	/**
	 * The method that persists User object data to the database.
	 * @param user - User object to be persisted.
	 */
	@Override
	public User create(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
			user = null;
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		} finally {
			session.close();
		}
		
		return user;
	}

	/**
	 * The method that retrieves all the persistent User objects from the 
	 * database.
	 * @return List of User objects from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> read() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<User> users = null;
		
		try {
			transaction = session.beginTransaction();
			users = session.createQuery("FROM User")
					.setCacheable(true).list();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		} finally {
			session.close();
		}
		
		return users;
	}
	
	/**
	 * The method that retrieves a list of persistent User objects from the 
	 * database depending on offset and limit specified.
	 * @param offset - index of the first record of the list to be returned.
	 * @param limit - number of records to be returned.
	 * @return List of User objects from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> read(int offset, int limit) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<User> users = null;
		
		try {
			transaction = session.beginTransaction();
			users = session.createQuery("FROM User")
					.setFirstResult(offset)
					.setMaxResults(limit)
					.setCacheable(true)
					.list();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		} finally {
			session.close();
		}
		
		return users;
	}

	/**
	 * The method that updates a specific persistent User object. 
	 * @param user - User object to be updated.
	 * @return int value that represents the affected database rows after 
	 * the update.
	 */
	@Override
	public int update(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int affectedRows = 0;
		
		try {
			transaction = session.beginTransaction();
			affectedRows = session.createQuery("UPDATE User SET "
					+ "username = :username, password = :password, "
					+ "emailAddress = :emailAddress, accessLevel "
					+ "= :accessLevel WHERE userId = :userId")
					.setString("username", user.getUsername())
					.setString("password", user.getPassword())
					.setString("emailAddress", user.getEmailAddress())
					.setInteger("accessLevel", user.getAccessLevel())
					.setInteger("userId", user.getUserId())
					.setCacheable(true)
					.executeUpdate();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		} finally {
			session.close();
		}
		
		return affectedRows;
	}

	/**
	 * The method that deletes a specific persistent User object.
	 * @param userId - Id of the User object to be deleted from the 
	 * database.
	 * @return int value that represent the affected database rows after 
	 * the delete.
	 */
	@Override
	public int delete(int userId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int affectedRows = 0;
		
		try {
			transaction = session.beginTransaction();
			affectedRows = session.createQuery("DELETE FROM User WHERE "
					+ "userId = :userId")
					.setInteger("userId", userId)
					.setCacheable(true)
					.executeUpdate();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		} finally {
			session.close();
		}
		
		return affectedRows;
	}

	/**
	 * The method that retrieves a specific persistent User object based 
	 * on the id of the user.
	 * @param userId - Id of the User object to be retrieved from the 
	 * database.
	 * @return User object from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User searchByUserId(int userId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<User> users = null;
		
		try {
			transaction = session.beginTransaction();
			users = session.createQuery("FROM User WHERE "
					+ "userId = :userId")
					.setInteger("userId", userId)
					.setCacheable(true)
					.list();
			transaction.commit();
		} catch (HibernateException exception) {
			rollbackTx(transaction);
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		} finally {
			session.close();
		}
		
		return (users.size() > 0) ? users.get(0) : null;
	}

	/**
	 * The method that retrieves a specific persistent User object based 
	 * on the username of the user.
	 * @param username - username of the User object to be retrieved from the 
	 * database.
	 * @return User object from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User searchByUsername(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<User> users = null;
		
		try {
			transaction = session.beginTransaction();
			users = session.createQuery("FROM User WHERE "
					+ "username = :username")
					.setString("username", username)
					.setCacheable(true)
					.list();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		} finally {
			session.close();
		}
		
		return (users.size() > 0) ? users.get(0) : null;
	}

	/**
	 * The method that retrieves a specific persistent User object based 
	 * on the email address of the user.
	 * @param userId - Id of the User object to be retrieved from the 
	 * database.
	 * @return User object from the database.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User searchByEmailAddress(String emailAddress) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<User> users = null;
		
		try {
			transaction = session.beginTransaction();
			users = session.createQuery("FROM User WHERE "
				+ "emailAddress = :emailAddress")
				.setString("emailAddress", emailAddress)
				.setCacheable(true)
				.list();
			transaction.commit();
		} catch(HibernateException exception) {
			rollbackTx(transaction);
			LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
		} finally {
			session.close();
		}
		
		return (users.size() > 0) ? users.get(0) : null;
	}
	
	/**
	 * A utility method that rolls back a Transaction object.
	 * @param transaction - Transaction object to be rolled back.
	 */
	private void rollbackTx(Transaction transaction) {
		if(transaction != null) {
			transaction.rollback();
		}
	}
 }
