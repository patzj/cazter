package org.cazter.api.router;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import org.cazter.api.Server;
import org.cazter.api.exception.ChannelNotFoundException;
import org.cazter.api.model.Channel;
import org.cazter.api.model.Message;

/**
 * The class that defines the abstraction of Session to Channel assignment, 
 * and Message routing.
 * @author patzj
 */
public class Router {
	
	private Channel channel;
	private Message message;
	private Session session;
	
	/**
	 * Router object constructor that takes no parameter.
	 */
	public Router() { }
	
	/**
	 * Router object constructor that takes three parameters.
	 * @param channelId - Channel id of the Channel object to be associated 
	 * with the Router object
	 * @param userId - String object that identifies a Session object in a 
	 * Channel
	 * @param session - Session object to be added to the Channel object
	 */
	public Router(int channelId, String userId, Session session) 
			throws ChannelNotFoundException {
		
		this.channel = Server.getChannels().get(channelId);
		this.session = session;
		
		if(channel == null) {
			throw new ChannelNotFoundException();
		} else {
			channel.addSession(userId, session);
		}
	}

	/**
	 * Get method for the Channel object associated with the Router object.
	 * @return current Channel of the Router object
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * Set method to associate a Channel object with the Router object.
	 * @param channel - Channel object to be associated with the Router object
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * Get method for the Message object currently associated with the Router 
	 * object.
	 * @return current Set of recipients
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * Set method for the Message object of the Router object.
	 * @param recipients - Set of recipients to be associated with Router 
	 * object
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
	
	/**
	 * Get method for the Session object currently associated with the Router 
	 * object.
	 * @return Session object associated with the Router object.
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Set method for the Session object of the Router object.
	 * @param session - Session object to be associated with the Router
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Sends the encoded Message object to recipients specified by the Message 
	 * object.
	 * @throws IOException
	 * @throws EncodeException
	 */
	public void send() throws IOException, EncodeException {
		Queue<Session> sendQueue = createSendQueue();
		
		if(sendQueue.size() > 0) {
			while(!sendQueue.isEmpty()) {
				sendQueue.poll().getBasicRemote().sendObject(message);
			}
		}
	}
	
	/**
	 * Creates a Queue of recipient Sessions of the current Message object 
	 * of the Router class.
	 * @return Queue of Session objects 
	 */
	private Queue<Session> createSendQueue() {
		Set<String> recipients = message.getTo();
		Queue<Session> sendQueue = new PriorityQueue<Session>(10, 
				new SessionComparator());
		Map<String, Session> sessions = channel.getSessions();
		
		for(String recipient : recipients) {
			Session session = sessions.get(recipient);
			
			if(session.isOpen()) {
				sendQueue.offer(session);
			}
		}
		
		sendQueue.offer(this.session);
		return sendQueue;
	}
	
	/**
	 * Private class that is used to override the default Comparator of the 
	 * PriorityQueue.
	 * @author patzj
	 */
	private class SessionComparator implements Comparator<Session> {

		@Override
		public int compare(Session session1, Session session2) {
			String userId1 = session1
					.getUserProperties()
					.get("userId")
					.toString();
			String userId2 = session2
					.getUserProperties()
					.get("userId")
					.toString();
			
			return userId1.compareTo(userId2);
		}		
	}
}
