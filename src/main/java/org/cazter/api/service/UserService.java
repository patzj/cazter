package org.cazter.api.service;

import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import org.cazter.api.dao.UserDao;
import org.cazter.api.dao.UserDaoImpl;
import org.cazter.api.model.User;

/**
 * Service class that provides data routing and retrieval between clients and 
 * User data access object.
 * @author patzj
 */
public class UserService {

	private UserDao userDao;
	
	/**
	 * UserService object constructor that takes no parameters. The method 
	 * that instantiates the UserDao.
	 */
	public UserService() {
		userDao = new UserDaoImpl();
	}
	
	/**
	 * The service method that routes the User object to be persisted.
	 * @param user - User object to be persisted.
	 * @return Persistent User object.
	 */
	public User create(User user) {
		User newUser = userDao.create(user);
		
		if(newUser == null) {
			throw new WebApplicationException();
		}
		
		return newUser;
	}
	
	/**
	 * The service method that retrieves the list of persistent User objects.
	 * @return List of User objects from the database.
	 */
	public List<User> read() {
		List<User> users = userDao.read();
		
		if(users == null || users.size() < 1) {
			throw new NotFoundException();
		}
		
		return users;
	}
	
	/**
	 * The service method that retrieves a list of persistent User objects 
	 * depending on offset and limit specified.
	 * @param offset - index of the first record of the list to be returned.
	 * @param limit - number of records to be returned.
	 * @return List of User objects from the database.
	 */
	public List<User> read(int offset, int limit) {
		List<User> users = userDao.read(offset, limit);
		
		if(users == null || users.size() < 1) {
			throw new NotFoundException();
		}
		
		return users;
	}
	
	/**
	 * The service method that routes the updates of persistent User 
	 * objects.
	 * @param user - User object to be updated.
	 * @return int value that represents the affected database rows after 
	 * the update.
	 */
	public int update(User user) {
		int affectedRows = userDao.update(user);
		
		if(affectedRows < 1) {
			throw new WebApplicationException();
		}
		
		return affectedRows;
	}
	
	/**
	 * The service method that routes the id of a specific persistent User 
	 * object to be deleted.
	 * @param userId - Id of the User object to be deleted from the 
	 * database.
	 * @return int value that represent the affected database rows after 
	 * the delete.
	 */
	public int delete(int userId) {
		int affectedRows = userDao.delete(userId);
		
		if(affectedRows < 1) {
			throw new WebApplicationException();
		}
		
		return affectedRows;
	}
	
	/**
	 * The service method that retrieves a specific persistent User object 
	 * based on the id of the User.
	 * @param userId - Id of the User object to be retrieved from the 
	 * database.
	 * @return User object from the database.
	 */
	public User searchByUserId(int userId) {
		User user = userDao.searchByUserId(userId);
		
		if(user == null) {
			throw new NotFoundException();
		}
		
		return user;
	}
	
	/**
	 * The service method that retrieves a specific persistent User object 
	 * based on the username of the User.
	 * @param userId - Id of the User object to be retrieved from the 
	 * database.
	 * @return User object from the database.
	 */
	public User searchByUsername(String username) {
		User user = userDao.searchByUsername(username);
		
		if(user == null) {
			throw new NotFoundException();
		}
		
		return user;
	}
	
	/**
	 * The service method that retrieves a specific persistent User object 
	 * based on the email address of the User.
	 * @param userId - Id of the User object to be retrieved from the 
	 * database.
	 * @return User object from the database.
	 */
	public User searchByEmailAddress(String emailAddress) {
		User user = userDao.searchByEmailAddress(emailAddress);
		
		if(user == null) {
			throw new NotFoundException();
		}
		
		return user;
	}
}
