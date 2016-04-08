package org.cazter.api.resource;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.cazter.api.model.HttpExceptionInfo;

/**
 * The class that handles the Response when WebApplicationException exception 
 * occurs.
 * @author patzj
 */
@Provider
public class WebApplicationExceptionMapper 
		implements ExceptionMapper<WebApplicationException> {

	private static final Logger LOGGER 
			= Logger.getLogger(WebApplicationExceptionMapper.class.getName());
	
	/**
	 * The method that generates the Response.
	 * @param exception - WebApplicationException object that contains data of the 
	 * exception that occured.
	 * @return HTTP Response.
	 */
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
