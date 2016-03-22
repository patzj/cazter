package org.cazter.api.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ServerConfigurator extends ServerEndpointConfig.Configurator {
	
	private static Set<String> originHeaders;
	
	/**
	 * The method that checks the origin header values from connecting clients 
	 * with the Set of origin header values contained in this class. The result 
	 * will determine whether to accept clients or not.
	 * @param originHeader - origin header value of connecting clients
	 * @return boolean
	 */
	@Override
	public boolean checkOrigin(String originHeader) {
		boolean flag = false;
		
		for(String originHeadersItem : originHeaders) {	
			if(originHeadersItem.equalsIgnoreCase(originHeader)) {
				return true;
			}
		}
		
		return flag;
	}

	/**
	 * The method that is used by the context listener to instantiate or 
	 * initialize a Set object to contain accepted origin header values.
	 */
	public static void initOriginHeaders() {
		originHeaders = Collections
				.synchronizedSet(new HashSet<String>());
	}
	
	/**
	 * Get method for the Set of origin header values contained in this class.
	 * @return Set of origin header values
	 */
	public static Set<String>getOriginHeaders() {
		return originHeaders;
	}
}
