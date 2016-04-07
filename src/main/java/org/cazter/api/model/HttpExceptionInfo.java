package org.cazter.api.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Model class for HTTP Exception data that are sent back to the clients..
 * @author patzj
 */
@XmlRootElement
public class HttpExceptionInfo {
	
	private int status;
	private String message;
	
	/**
	 * HttpExceptionInfo object constructor that takes no arguments.
	 */
	public HttpExceptionInfo() { }
	
	/**
	 * HttpExceptionInfo object constructor that takes two parameters.
	 * @param status - int value of the HTTP Status code of the response / 
	 * exception.
	 * @param message - String object that represents the message of the 
	 * HTTP error thrown.
	 */
	public HttpExceptionInfo(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	/**
	 * Get method of the code instance variable.
	 * @return int value of the code instance variable.
	 */
	public int getCode() {
		return status;
	}
	
	/**
	 * Set method of the code instance variable.
	 * @param status - Status code that represents the HTTP status code of 
	 * HTTP exception thrown.
	 */
	public void setCode(int status) {
		this.status = status;
	}
	
	/**
	 * Get method of the message instance variable.
	 * @return String value of the message instance variable.
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Set method of the message instance variable.
	 * @param message - String value that represents the message of the 
	 * HTTP exception thrown. 
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
