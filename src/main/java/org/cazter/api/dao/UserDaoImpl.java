package org.cazter.api.dao;

import java.util.List;
import org.cazter.api.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	public UserDaoImpl() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder
				= new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	@Override
	public User create(User user) {
		startSession();
		session.save(user);
		endSession();
		
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> read() {
		List<User> users;
		
		startSession();
		Query query = session.createQuery("FROM User");
		query.setCacheable(true);
		users = query.list();
		endSession();
		
		return users;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> read(int offset, int limit) {
		List<User> users;
		
		startSession();
		Query query = session.createQuery("FROM User");
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		query.setCacheable(true);
		users = query.list();
		endSession();
		
		return users;
	}

	@Override
	public int update(User user) {
		int affectedRows;
		
		startSession();
		Query query = session.createQuery("UPDATE User SET "
				+ "username = :username, password = :password, "
				+ "emailAddress = :emailAddress, accessLevel = :accessLevel "
				+ "WHERE userId = :userId");
		query.setString("username", user.getUsername())
				.setString("password", user.getPassword())
				.setString("emailAddress", user.getEmailAddress())
				.setInteger("accessLevel", user.getAccessLevel())
				.setInteger("userId", user.getUserId());
		affectedRows = query.executeUpdate();
		endSession();
		
		return affectedRows;
	}

	@Override
	public int delete(int userId) {
		int affectedRows;
		
		startSession();
		Query query = session.createQuery("DELETE FROM User WHERE "
				+ "userId = :userId");
		query.setInteger("userId", userId);
		query.setCacheable(true);
		affectedRows = query.executeUpdate();
		endSession();
		
		return affectedRows;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User searchByUserId(int userId) {
		List<User> users;
		
		startSession();
		Query query = session.createQuery("FROM User WHERE "
				+ "userId = :userId");
		query.setInteger("userId", userId);
		query.setCacheable(true);
		users = query.list();
		endSession();
		
		return (users.size() > 0) ? users.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User searchByUsername(String username) {
		List<User> users;
		
		startSession();
		Query query = session.createQuery("FROM User WHERE "
				+ "username = :username");
		query.setString("username", username);
		query.setCacheable(true);
		users = query.list();
		endSession();
		
		return (users.size() > 0) ? users.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User searchByEmailAddress(String emailAddress) {
		List<User> users;
		
		startSession();
		Query query = session.createQuery("FROM User WHERE "
				+ "emailAddress = :emailAddress");
		query.setString("emailAddress", emailAddress);
		users = query.list();
		query.setCacheable(true);
		endSession();
		
		return (users.size() > 0) ? users.get(0) : null;
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
