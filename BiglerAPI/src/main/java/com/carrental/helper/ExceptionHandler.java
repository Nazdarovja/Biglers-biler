package com.carrental.helper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * The Class ExceptionHandler.
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

/* This Method will handle application level exceptions and will generate error JSON in return
 * @param Exception occurred
 * @return Response Error JSON
 */
@Produces(MediaType.APPLICATION_JSON)
public Response toResponse(Exception exception){

	return Response.status(Response.Status.NOT_FOUND).entity(ErrorMessageUtility
			.getErrorJson(500 , "something went wrong",	"")).type("application/json").build();
}
}