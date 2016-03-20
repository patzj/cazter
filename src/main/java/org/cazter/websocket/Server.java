package org.cazter.websocket;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.cazter.websocket.config.ServerConfigurator;
import org.cazter.websocket.encoder.MessageEncoder;
import org.cazter.websocket.decoder.MessageDecoder;

/**
 * The server end point class that will be made available to client end points 
 * upon deployment, and will do the handling of client end points' session.
 * @author patzj
 */
@ServerEndpoint(value="/server",
		configurator=ServerConfigurator.class,
		encoders={MessageEncoder.class},
		decoders={MessageDecoder.class})
public class Server {

	@OnOpen
	public void open(Session session) {
		
	}
	
	@OnMessage
	public void message(Session session, String message) {
		
	}
	
	@OnError
	public void error(Session session, Throwable err) {
		
	}
	
	@OnClose
	public void close(Session session, CloseReason reason) {
		
	}
}
