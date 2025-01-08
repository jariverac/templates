package ${{ values.package }}.utils.errors;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import ${{ values.package }}.utils.errors.dtos.Error;
import ${{ values.package }}.utils.errors.dtos.ErrorResponse;
import org.slf4j.MDC;

import java.util.List;

@Provider
@Slf4j
@SuppressWarnings("unused")
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    @Override
    public Response toResponse(ValidationException e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(400, e.getMessage(), List.of(new Error(400, e.getMessage())));
        return Response.status(Response.Status.BAD_REQUEST).header("TRNID", MDC.get("trnID")).entity(errorResponse).build();
    }
}
