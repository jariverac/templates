package ${{ values.package }}.utils.errors;

import jakarta.ws.rs.BadRequestException;
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
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(400, CodeMessage.Code400, List.of(new Error(400, e.getMessage())));
        return Response.status(Response.Status.BAD_REQUEST).header("TRNID", MDC.get("trnID")).entity(errorResponse).build();
    }
}
