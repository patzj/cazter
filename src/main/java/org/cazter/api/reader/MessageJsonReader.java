package org.cazter.api.reader;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A helper class for decoding JSON data.
 * @author patzj
 */
public class MessageJsonReader {
	
	/**
	 * The method that checks if the data is a valid JSON for decoding.
	 * @param json - Data to be checked for validity.
	 * @return boolean
	 */
	public boolean isValidJson(String json) {
		boolean flag = true;
			
		try {
			Json.createReader(new StringReader(json)).readObject();
		} catch(JsonException exception) {
			flag = false;
		}
		
		return flag;
	}

	/**
	 * The method that is used to read a certain value from a JSON data.
	 * @param json - JSON data.
	 * @param key - Value to be read.
	 * @return String object that represents a single data JSON property.
	 */
	public String decodeJsonData(String json, String key) {
		
		JSONObject jsonObject = new JSONObject(json);
		return jsonObject.get(key).toString();
	}
	
	/**
	 * The method that is used to read an array of values from a JSON data.
	 * @param json - JSON data.
	 * @param key - value to be read.
	 * @return Set of String values that represents a collection JSON property.
	 */
	public Set<String> decodeJsonArrayData(String json, String key) {
		
		Set<String> jsonData = new HashSet<String>();
		JSONObject jsonObject = new JSONObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray(key);
		Iterator<Object> iterator = jsonArray.iterator();
		
		while(iterator.hasNext()) {
			jsonData.add(iterator.next().toString());

		}
		
		return jsonData;
	}
}
