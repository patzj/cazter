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
	
	public Channel() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Map<String, Session> getSessions() {
		return sessions;
	}

	public void setSessions(Map<String, Session> sessions) {
		this.sessions = sessions;
	}
	
	public void addSession(String userId, Session session) {
		sessions.put(userId, session);
	}
}
