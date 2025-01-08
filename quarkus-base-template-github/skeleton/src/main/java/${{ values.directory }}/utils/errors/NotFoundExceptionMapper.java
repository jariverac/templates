package ${{ values.package }}.utils.errors;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import ${{ values.package }}.utils.defaults.CodeMessage;
import ${{ values.package }}.utils.errors.dtos.Error;
import ${{ values.package }}.utils.errors.dtos.ErrorResponse;
import org.slf4j.MDC;

import java.util.List;

@Slf4j
@Provider
@SuppressWarnings("unused")
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(400, CodeMessage.Code404, List.of(new Error(404, e.getMessage())));
        return Response.status(Response.Status.NOT_FOUND).header("TRNID", MDC.get("trnID")).entity(errorResponse).build();
    }
}
