package org.cazter.api.service;

import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import org.cazter.api.dao.UserDao;
import org.cazter.api.dao.UserDaoImpl;
import org.cazter.api.model.User;

public class UserService {

	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDaoImpl();
	}
	
	public User create(User user) {
		User newUser = userDao.create(user);
		
		if(newUser == null) {
			throw new WebApplicationException();
		}
		
		return newUser;
	}
	
	public List<User> read() {
		List<User> users = userDao.read();
		
		if(users == null || users.size() < 1) {
			throw new NotFoundException();
		}
		
		return users;
	}
	
	public List<User> read(int offset, int limit) {
		List<User> users = userDao.read(offset, limit);
		
		if(users == null || users.size() < 1) {
			throw new NotFoundException();
		}
		
		return users;
	}
	
	public int update(User user) {
		int affectedRows = userDao.update(user);
		
		if(affectedRows < 1) {
			throw new WebApplicationException();
		}
		
		return affectedRows;
	}
	
	public int delete(int userId) {
		int affectedRows = userDao.delete(userId);
		
		if(affectedRows < 1) {
			throw new WebApplicationException();
		}
		
		return affectedRows;
	}
	
	public User searchByUserId(int userId) {
		User user = userDao.searchByUserId(userId);
		
		if(user == null) {
			throw new NotFoundException();
		}
		
		return user;
	}
	
	public User searchByUsername(String username) {
		User user = userDao.searchByUsername(username);
		
		if(user == null) {
			throw new NotFoundException();
		}
		
		return user;
	}
	
	public User searchByEmailAddress(String emailAddress) {
		User user = userDao.searchByEmailAddress(emailAddress);
		
		if(user == null) {
			throw new NotFoundException();
		}
		
		return user;
	}
}
