package org.cazter.websocket.config;

import java.util.List;
import javax.websocket.Extension;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class ServerConfigurator extends ServerEndpointConfig.Configurator {

	@Override
	public boolean checkOrigin(String originHeaderValue) {
		// TODO Auto-generated method stub
		return super.checkOrigin(originHeaderValue);
	}

	@Override
	public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
		// TODO Auto-generated method stub
		return super.getEndpointInstance(endpointClass);
	}

	@Override
	public List<Extension> getNegotiatedExtensions(List<Extension> installed, List<Extension> requested) {
		// TODO Auto-generated method stub
		return super.getNegotiatedExtensions(installed, requested);
	}

	@Override
	public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
		// TODO Auto-generated method stub
		return super.getNegotiatedSubprotocol(supported, requested);
	}

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		// TODO Auto-generated method stub
		super.modifyHandshake(sec, request, response);
	}

}
