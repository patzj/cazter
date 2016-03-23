package org.cazter.api.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model class for data that traverses the server.
 * @author patzj
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String from;
	private Set<String> to = new HashSet<String>();
	private String content;
	private long timestamp;
	
	/**
	 * Message object constructor that takes no parameters.
	 */
	public Message() { }
	
	/**
	 * Get method of the from instance variable.
	 * @return String value of the from instance variable.
	 */
	public String getFrom() {
		return from;
	}
	
	/**
	 * Set method of the from instance variable.
	 * @param from - String object that represents the sender of the Message 
	 * object.
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	
	/**
	 * Get method of the to instance variable.
	 * @return Set of String values of the to instance variable.
	 */
	public Set<String> getTo() {
		return to;
	}
	
	/**
	 * Set method of the to instance variable.
	 * @param to - Set of String objects that represents the recipients of the 
	 * Message object.
	 */
	public void setTo(Set<String> to) {
		this.to = to;
	}
	
	/**
	 * Get method of the content instance variable.
	 * @return String value of the content instance variable.
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Set method of the content instance variable.
	 * @param content - String object that represents the content of the Message 
	 * object.
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * Get method of the timestamp instance variable.
	 * @return timestamp in milliseconds.
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Set method of the timestamp instance variable.
	 * @param timestamp - long integer that represents the timestamp in 
	 * milliseconds.
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * The method that adds or puts a single recipient to the Set of 
	 * recipients.
	 * @param to - String object that represents a single recipient of the 
	 * Message object
	 */
	public void addTo(String to) {
		this.to.add(to);
	}
}
