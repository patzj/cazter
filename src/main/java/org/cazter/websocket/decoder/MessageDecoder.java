package org.cazter.websocket.decoder;

import java.util.Date;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import org.cazter.websocket.model.Message;
import org.cazter.websocket.reader.MessageJsonReader;

/**
 * Decoder class specific for Message objects. This class decodes a JSON 
 * data from client end points into a Message object.
 * @author patzj
 */
public class MessageDecoder implements Decoder.Text<Message> {

	@Override
	public void destroy() { }

	@Override
	public void init(EndpointConfig config) { }

	/**
	 * This method do the decoding of JSON data from client end points into 
	 * a Message object.
	 * @param json - JSON data from client end point
	 * @throws DecodeException
	 */
	@Override
	public Message decode(String json) throws DecodeException {
		
		Message message = new Message();
		MessageJsonReader reader = new MessageJsonReader();
		
		message.setFrom(reader.decodeJsonData(json, "from"));
		message.setTo(reader.decodeJsonArrayData(json, "to"));
		message.setContent(reader.decodeJsonData(json, "content"));
		message.setTimestamp(new Date().getTime());
		
		return message;
	}

	/**
	 * This method checks of the data from the client end point is a valid 
	 * JSON data.
	 * @param json - JSON data from client end point
	 * @return boolean
	 */
	@Override
	public boolean willDecode(String json) {
		
		MessageJsonReader reader = new MessageJsonReader();
		return reader.isValidJson(json);
	}
}
