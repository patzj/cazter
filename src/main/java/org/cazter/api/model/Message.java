package org.cazter.api.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Container class for data from clients traversing the server.
 * @author patzj
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String from;
	private Set<String> to;
	private String content;
	private long timestamp;
	
	public Message() { }
	
	public String getFrom() {
		return from;
	}
	
	public void setFrom(String from) {
		this.from = from;
	}
	
	public Set<String> getTo() {
		return to;
	}
	
	public void setTo(Set<String> to) {
		this.to = to;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
