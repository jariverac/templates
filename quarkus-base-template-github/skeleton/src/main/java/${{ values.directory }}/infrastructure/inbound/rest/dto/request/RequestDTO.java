package ${{ values.package }}.infrastructure.inbound.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@RegisterForReflection
public class RequestDTO{
    @JsonProperty("mensaje")
    @NotEmpty
    public String mensaje;
    @JsonProperty("otroMensaje")
    @NotEmpty
    public String otro;
}
