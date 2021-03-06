package org.cazter.api;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.cazter.api.config.ServerConfigurator;
import org.cazter.api.decoder.MessageDecoder;
import org.cazter.api.encoder.MessageEncoder;
import org.cazter.api.exception.ChannelNotFoundException;
import org.cazter.api.model.Channel;
import org.cazter.api.model.Message;
import org.cazter.api.router.Router;

/**
 * The server end point class that will be made available to client end points 
 * upon deployment, and will do the handling of client end points' session.
 * @author patzj
 */
@SuppressWarnings("unused")
@ServerEndpoint(value="/server/{channelId}/{userId}",
		configurator=ServerConfigurator.class,
		encoders={MessageEncoder.class},
		decoders={MessageDecoder.class})
public class Server {

	private static Map<String, Channel> channels;
	private Router router;
	
	@OnOpen
	public void open(Session session, EndpointConfig config, 
				@PathParam("channelId") String channelId,
				@PathParam("userId") String userId) {
		
		session.getUserProperties().put("userId", userId);
		
		try {
			router = new Router(channelId, userId, session);
		} catch(ChannelNotFoundException exception) {
			try {
				session.close(new CloseReason(
						CloseReason.CloseCodes.CANNOT_ACCEPT, 
						exception.getMessage()));
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	
	@OnMessage
	public void message(Session session, Message message) {
		router.setMessage(message);
		try {
			router.send();
		} catch (IOException | EncodeException exception) {
			exception.printStackTrace();
		}
	}
	
	@OnError
	public void error(Session session, Throwable throwable) {
		throwable.printStackTrace();
	}
	
	@OnClose
	public void close(Session session, CloseReason reason) {
		router.getChannel().removeSession(session
				.getUserProperties()
				.get("userId")
				.toString());
	}
	
	/**
	 * The method that is used by the context listener to instantiate or 
	 * initialized a Map object to contain Channel objects.
	 */
	public static void initChannels() {
		channels = Collections
				.synchronizedMap(new HashMap<String, Channel>());
	}
	
	/**
	 * Get method for the Map of Channel objects contained in this class.
	 * @return Map of Channel objects
	 */
	public static Map<String, Channel> getChannels() {
		return channels;
	}
}
