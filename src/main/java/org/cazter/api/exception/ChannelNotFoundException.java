package org.cazter.api.exception;

public class ChannelNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public ChannelNotFoundException() {
		super("Specified channel not found.");
	}
	
	public ChannelNotFoundException(String message) {
		super(message);
	}
}