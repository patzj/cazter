package org.cazter.api.writer;

import java.util.Set;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

/**
 * A helper class for encoding JSON data.
 * @author patzj
 */
public class MessageJsonWriter {
	
	/**
	 * The method that is used to create a JSON array builder from a set 
	 * of String objects.
	 * @param set - Set of String objects to be encoded.
	 * @return JsonArrayBuilder that represent a collection JSON property.
	 */
	public JsonArrayBuilder createArrayBuilder(Set<String> set) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for(String val : set) {
			jsonArrayBuilder.add(val);
		}
		
		return jsonArrayBuilder;
	}
}
