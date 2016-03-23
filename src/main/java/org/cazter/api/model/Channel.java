package org.cazter.api.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.websocket.Session;

/**
 * Model class for the containment of Session objects.
 * @author patzj
 */
@Entity
@Table(name="Channel")
public class Channel {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Id")
	private int id;
	@Column(name="Origin")
	private String origin;
	@Transient
	private Map<String, Session> sessions = new HashMap<String, Session>();
	
	/**
	 * Channel object constructor that takes no parameters.
	 */
	public Channel() { }

	/**
	 * Get method of the id instance variable.
	 * @return int value of the id instance variable.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set method of the id instance variable.
	 * @param id - int value that represents the id of the Channel.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get method of the origin instance variable.
	 * @return String value that represents the client origin header accepted 
	 * by the Channel.
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Set method of the origin instance variable.
	 * @param origin - String object that represents the client origin header 
	 * accepted by the Channel.
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Get method of the Map of Session objects.
	 * @return Map of Session objects associated with the Channel.
	 */
	public Map<String, Session> getSessions() {
		return sessions;
	}

	/**
	 * Set method of the Map of Session objects.
	 * @param sessions - Map of Session objects to be associated with the 
	 * Channel.
	 */
	public void setSessions(Map<String, Session> sessions) {
		this.sessions = sessions;
	}
	
	/**
	 * The method that adds or puts a single Session to the Map of Session  
	 * objects associated with the Channel.
	 * @param userId - Key value of the Session object.
	 * @param session - Session object to be associated with the Channel.
	 */
	public void addSession(String userId, Session session) {
		sessions.put(userId, session);
	}
	
	/**
	 * The method that removes a single Session from the Map of Session  
	 * objects associated with the Channel.
	 * @param userId - Key value of the Session object.
	 */
	public void removeSession(String userId) {
		sessions.remove(userId);
	}
}
