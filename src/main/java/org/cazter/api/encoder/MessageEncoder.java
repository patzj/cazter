package org.cazter.api.encoder;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import org.cazter.api.model.Message;
import org.cazter.api.writer.MessageJsonWriter;

/**
 * the encoder class specific for Message objects. This class encodes Message 
 * object into a JSON data.
 * @author patzj
 */
public class MessageEncoder implements Encoder.Text<Message> {

	@Override
	public void destroy() { }

	@Override
	public void init(EndpointConfig config) { }

	/**
	 * @param message - Message object to be encoded into JSON data.
	 * @return String object formatted as JSON.
	 * @throws EncodeException if an encoding issue occur.
	 */
	@Override
	public String encode(Message message) throws EncodeException {
		MessageJsonWriter writer = new MessageJsonWriter();
		
		return Json.createObjectBuilder()
				.add("from", message.getFrom())
				.add("to", writer.createArrayBuilder(message.getTo()))
				.add("content", message.getContent())
				.add("timestamp", message.getTimestamp())
				.build()
				.toString();
	}
}
