package ${{ values.package }}.utils.utils.errors;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import ${{ values.package }}.utils.defaults.CodeMessage;
import ${{ values.package }}.utils.errors.dtos.Error;
import ${{ values.package }}.utils.errors.dtos.ErrorResponse;
import org.slf4j.MDC;

import java.util.List;

@Provider
@Slf4j
@SuppressWarnings("unused")
public class ThrowableMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        log.error(e.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(500, CodeMessage.Code500, List.of(new Error(500, e.getMessage())));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header("TRNID", MDC.get("trnID")).entity(errorResponse).build();
    }

}
