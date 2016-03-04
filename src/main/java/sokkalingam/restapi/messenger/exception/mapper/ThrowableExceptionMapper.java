package sokkalingam.restapi.messenger.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import sokkalingam.restapi.messenger.model.ErrorMessage;

@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable th) {
		ErrorMessage errorMessage = new ErrorMessage(500, th.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
	}

}
