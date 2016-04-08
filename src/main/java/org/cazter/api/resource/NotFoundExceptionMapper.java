package org.cazter.api.resource;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.cazter.api.model.HttpExceptionInfo;

/**
 * The class that handles the Response when NotFoundException exception 
 * occurs.
 * @author patzj
 */
@Provider
public class NotFoundExceptionMapper 
		implements ExceptionMapper<NotFoundException>{

	private final static Logger LOGGER 
			= Logger.getLogger(NotFoundExceptionMapper.class.getName());
	
	/**
	 * The method that generates the Response.
	 * @param exception - NotFoundException object that contains data of the 
	 * exception that occured.
	 * @return HTTP Response.
	 */
	@Override
	public Response toResponse(NotFoundException exception) {
		HttpExceptionInfo httpExceptionInfo 
				= new HttpExceptionInfo(Status.NOT_FOUND.getStatusCode(), 
						exception.getMessage());
		
		LOGGER.log(Level.INFO, exception.getMessage());
		
		return Response.status(Status.NOT_FOUND)
				.entity(httpExceptionInfo)
				.build();
	}
}
