package ${{ values.package }}.infrastructure.inbound.rest;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import ${{ values.package }}.application.interfaces.inbound.ProcessRequest;
import ${{ values.package }}.infrastructure.inbound.rest.dto.request.RequestDTO;
import ${{ values.package }}.infrastructure.inbound.rest.dto.response.ResponseDTO;
import ${{ values.package }}.utils.defaults.CodeMessage;
import ${{ values.package }}.utils.errors.dtos.ErrorResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.slf4j.MDC;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/scaffold")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class RestAPI {
    @Inject
    ProcessRequest processRequest;
    @POST
    @APIResponses({
            @APIResponse(responseCode = CodeMessage.Code200,
                    description = "Reservation confirmed",
                    content = @Content(mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = ResponseDTO.class))
            ),
            @APIResponse(
                    responseCode = "400",
                    description = CodeMessage.Code400,
                    content = @Content(mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            ),
            @APIResponse(
                    responseCode = "401",
                    description = CodeMessage.Code401,
                    content = @Content(mediaType = APPLICATION_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public Response postRequest(@Valid RequestDTO request) {
        log.info("Request: {}", request);

        return Response.ok(processRequest.process(request))
                .header("TRNID", MDC.get("trnID"))
                .build();
    }
}
