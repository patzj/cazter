package org.cazter.api.dao;

import java.util.List;
import org.cazter.api.model.User;

/**
 * User data access object interface.
 * @author patzj
 */
public interface UserDao {

	public User create(User user);
	public List<User> read();
	public List<User> read(int offset, int limit);
	public int update(User user);
	public int delete(int userId);
	public User searchByUserId(int userId);
	public User searchByUsername(String username);
	public User	searchByEmailAddress(String emailAddress);
}
