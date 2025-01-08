package ${{ values.package }}.infrastructure.inbound.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class ResponseDTO {
    @JsonProperty("status")
    public String status;
    @JsonProperty("mensaje")
    public String mensaje;
}
