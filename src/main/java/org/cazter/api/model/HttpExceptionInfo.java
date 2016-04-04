package org.cazter.api.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HttpExceptionInfo {
	
	private int status;
	private String message;
	
	public HttpExceptionInfo() { }
	
	public HttpExceptionInfo(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public int getCode() {
		return status;
	}
	
	public void setCode(int status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
