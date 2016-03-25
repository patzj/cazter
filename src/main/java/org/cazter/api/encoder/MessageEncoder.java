package org.cazter.api.encoder;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import org.cazter.api.model.Message;
import org.json.JSONObject;

/**
 * The encoder class specific for Message objects. This class encodes Message 
 * object into a JSON data.
 * @author patzj
 */
public class MessageEncoder implements Encoder.Text<Message> {

	@Override
	public void destroy() { }

	@Override
	public void init(EndpointConfig config) { }

	/**
	 * The method that do the encoding of Message object from server end point 
	 * into a JSON data.
	 * @param message - Message object to be encoded into JSON data.
	 * @return String object formatted as JSON.
	 * @throws EncodeException if an encoding issue occur.
	 */
	@Override
	public String encode(Message message) throws EncodeException {
		JSONObject jsonObject = new JSONObject();
		
		return jsonObject.put("from", message.getFrom())
			.put("to", message.getTo())
			.put("content", message.getContent())
			.put("timestamp", message.getTimestamp())
			.toString();
	}
}
