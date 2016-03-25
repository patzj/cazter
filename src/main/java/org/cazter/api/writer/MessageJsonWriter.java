package org.cazter.api.writer;

import java.util.Set;
import org.json.JSONArray;

/**
 * A helper class for encoding JSON data.
 * @author patzj
 */
public class MessageJsonWriter {
	
	/**
	 * The method that is used to create a JSON array from a set of String 
	 * objects.
	 * @param set - Set of String objects to be encoded.
	 * @return JSONArray that represent a collection JSON property.
	 */
	public JSONArray createArray(Set<String> set) {
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(set);

		return jsonArray;
	}
}
