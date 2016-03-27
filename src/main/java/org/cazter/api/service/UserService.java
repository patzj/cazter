package org.cazter.api.service;

import java.util.List;
import org.cazter.api.dao.UserDao;
import org.cazter.api.dao.UserDaoImpl;
import org.cazter.api.model.User;

public class UserService {
	
	private UserDao userDao;
	
	public UserService() {
		userDao = new UserDaoImpl();
	}
	
	public User create(User user) {
		return userDao.create(user);
	}
	
	public List<User> read() {
		return userDao.read();
	}
	
	public List<User> read(int offset, int limit) {
		return userDao.read(offset, limit);
	}
	
	public int update(User user) {
		return userDao.update(user);
	}
	
	public int delete(int userId) {
		return userDao.delete(userId);
	}
	
	public User searchByUserId(int userId) {
		return userDao.searchByUserId(userId);
	}
	
	public User searchByUsername(String username) {
		return userDao.searchByUsername(username);
	}
	
	public User searchByEmailAddress(String emailAddress) {
		return userDao.searchByEmailAddress(emailAddress);
	}
}
