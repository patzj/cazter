package org.cazter.websocket.reader;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonValue;
import javax.websocket.DecodeException;

/**
 * A helper class for decoding JSON data.
 * @author patzj
 */
public class MessageJsonReader {
	
	/**
	 * This method checks if the data is a valid JSON for decoding.
	 * @param json - data to be checked for validity
	 * @return boolean
	 */
	public boolean isValidJson(String json) {
		
		boolean flag = true;
			
		try {
			Json.createReader(new StringReader(json)).readObject();
		} catch(JsonException err) {
			flag = false;
		}
		
		return flag;
	}

	/**
	 * This method is used to read a certain value from a JSON data.
	 * @param json - JSON data
	 * @param key - value to be read
	 * @return String
	 * @throws DecodeException
	 */
	public String decodeJsonData(String json, String key) 
			throws DecodeException {
		
		return Json.createReader(new StringReader(json))
				.readObject()
				.getString(key);
	}
	
	/**
	 * This method is used to read an array of values from a JSON data.
	 * @param json - JSON data
	 * @param key - value to be read
	 * @return Set<String>
	 * @throws DecodeException
	 */
	public Set<String> decodeJsonArrayData(String json, String key) 
			throws DecodeException {
		
		Set<String> jsonData = new HashSet<String>();
		JsonArray jsonArray = Json.createReader(new StringReader(json))
				.readObject().getJsonArray(key);
		
		for(JsonValue jsonValue : jsonArray) {
			jsonData.add(jsonValue.toString());
		}
		
		return jsonData;
	}
}
