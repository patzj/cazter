package org.cazter.api.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Model class for the users of the system.
 * @author patzj
 */
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
@Table(name="User")
@XmlRootElement
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="UserId", nullable=false)
	private int userId;
	@Column(name="Username", nullable=false, unique=true)
	private String username;
	@Column(name="Password", nullable=false)
	private String password;
	@Column(name="EmailAddress", nullable=false, unique=true)
	private String emailAddress;
	@Column(name="AccessLevel", nullable=false)
	private int accessLevel;
	
	/**
	 * User object constructor that takes no arguments.
	 */
	public User() { }
	
	/**
	 * Get method of the userId instance variable.
	 * @return int value of the userId instance variable.
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Set method of the userId instance variable.
	 * @param userId - String object that represents the id of the user.
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * Get method of the username instance variable.
	 * @return String value of the username instance variable. 
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Set method of the username instance variable.
	 * @param username - String object that represents the username of the 
	 * user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Get method of the password instance variable.
	 * @return string value of the password instance variable.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set method of th password instance variable.
	 * @param password - String object that represents the password of the 
	 * user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Get method of the emailAddress instance variable.
	 * @return String value if the emailAddress instance variable.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * Set method of the emailAddress instance variable.
	 * @param emailAddress - String object that represents the email address 
	 * of the user.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	/**
	 * Get method of the accessLevel instance variable.
	 * @return int value of the accessLevel instance variable ranging from 
	 * 0 to 100.
	 */
	public int getAccessLevel() {
		return accessLevel;
	}
	
	/**
	 * Set method of the accessLevel instance variable.
	 * @param accessLevel - int value that represents the access level of the 
	 * user.
	 */
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
}
