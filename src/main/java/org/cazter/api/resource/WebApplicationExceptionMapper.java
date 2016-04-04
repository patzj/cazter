package org.cazter.api.resource;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.cazter.api.model.HttpExceptionInfo;

@Provider
public class WebApplicationExceptionMapper 
		implements ExceptionMapper<WebApplicationException> {

	private static final Logger LOGGER 
			= Logger.getLogger(WebApplicationExceptionMapper.class.getName());
	
	@Override
	public Response toResponse(WebApplicationException exception) {
		HttpExceptionInfo httpExceptionInfo 
				= new HttpExceptionInfo(exception.getResponse()
						.getStatusInfo()
						.getStatusCode(), 
				exception.getMessage());
		
		LOGGER.log(Level.SEVERE, exception.toString());
		
		return Response
				.status(Status.fromStatusCode(httpExceptionInfo.getCode()))
				.entity(httpExceptionInfo)
				.build();
	}
}
