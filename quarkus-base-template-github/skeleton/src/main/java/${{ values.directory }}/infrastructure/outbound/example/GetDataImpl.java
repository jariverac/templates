package ${{ values.package }}.infrastructure.outbound.example;

import jakarta.enterprise.context.RequestScoped;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ${{ values.package }}.application.interfaces.outbound.GetData;
import ${{ values.package }}.infrastructure.inbound.rest.dto.request.RequestDTO;


@RequestScoped
@Slf4j
public class GetDataImpl implements GetData {


    @SneakyThrows
    public boolean get(RequestDTO requestDTO) {
        return true;
    }
}
