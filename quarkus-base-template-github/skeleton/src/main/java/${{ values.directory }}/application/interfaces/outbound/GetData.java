package ${{ values.package }}.application.interfaces.outbound;

import ${{ values.package }}.infrastructure.inbound.rest.dto.request.RequestDTO;

public interface GetData {
    boolean get(RequestDTO requestDTO);
}
