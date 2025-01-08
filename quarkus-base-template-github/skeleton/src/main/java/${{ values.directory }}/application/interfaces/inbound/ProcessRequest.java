package ${{ values.package }}.application.interfaces.inbound;

import ${{ values.package }}.infrastructure.inbound.rest.dto.request.RequestDTO;
import ${{ values.package }}.infrastructure.inbound.rest.dto.response.ResponseDTO;

public interface ProcessRequest {
    ResponseDTO process(RequestDTO requestDTO);
}
