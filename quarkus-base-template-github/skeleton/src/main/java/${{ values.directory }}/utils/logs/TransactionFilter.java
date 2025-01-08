package ${{ values.package }}.utils.logs;

import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.MDC;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Provider
public class TransactionFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext context) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
        String formattedDateTime = dateTime.format(formatter);

        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString().replace("-", "");
        uniqueId = uniqueId.substring(0, Math.min(uniqueId.length(), 10));

        int remainingLength = 15 - formattedDateTime.length();
        if (uniqueId.length() > remainingLength) {
            uniqueId = uniqueId.substring(0, remainingLength);
        }
        MDC.put("trnID", formattedDateTime +  uniqueId);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MDC.remove("trnID");
    }

}
