package org.cazter.api.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.websocket.Session;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for the containment of Session objects.
 * @author patzj
 */
@Entity
@Table(name="Channel")
@XmlRootElement
public class Channel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="Id", nullable=false)
	private String id;
	@Column(name="Origin", nullable=false)
	private String origin;
	@Column(name="OwnerId", nullable=false)
	private int ownerId;
	@Transient
	private Map<String, Session> sessions = new HashMap<String, Session>();
	
	/**
	 * Channel object constructor that takes no parameters.
	 */
	public Channel() { }

	/**
	 * Get method of the id instance variable.
	 * @return String value of the id instance variable in hex.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set method of the id instance variable.
	 * @param id - String value that represents the id of the Channel in hex.
	 */
	public void setId(String id) {
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
	 * Get method of the ownerId instance variable.
	 * @return int value that represents the owner of the Channel.
	 */
	public int getOwnerId() {
		return ownerId;
	}

	/**
	 * Set method of the ownerId instance variable.
	 * @param ownerId - int value that represents the owner of the Channel.
	 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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
