package org.cazter.api.dao;

import java.util.List;
import org.cazter.api.model.User;

/**
 * User data access object interface.
 * @author patzj
 */
public interface UserDao {

	public int create(User user);
	public List<User> read();
	public int update(User user);
	public int delete(String username);
	public User search(User user);
	public User searchByUsername(String username);
	public User	searchByEmailAddress(String emailAddress);
}
