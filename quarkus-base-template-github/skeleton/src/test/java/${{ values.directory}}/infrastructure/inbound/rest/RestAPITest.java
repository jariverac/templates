package ${{ values.package }}.infrastructure.inbound.rest;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.ws.rs.core.MediaType;
{package}.application.interfaces.inbound.ProcessRequest;
{package}.infrastructure.inbound.rest.dto.request.RequestDTO;
import ${{ values.package }}.infrastructure.inbound.rest.dto.response.ResponseDTO;
{package}.utils.errors.dtos.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class RestAPITest {
    @InjectMock
    ProcessRequest processRequestPort;

    @Test
    @TestSecurity(authorizationEnabled = false)
    void archetype200() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setMensaje("test");

        ResponseDTO respuesta = new ResponseDTO();
        respuesta.setMensaje("test");
        Mockito.when(processRequestPort.process(any(RequestDTO.class))).thenReturn(respuesta);

        ResponseDTO response = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDTO)
                .when().post("/scaffold")
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON)
                .extract()
                .as(ResponseDTO.class);
        assertEquals("test", response.getMensaje());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void archetypeBody400() {

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setMensaje("test");
        ErrorResponse response = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDTO)
                .when().post("/scaffold")
                .then()
                .statusCode(400)
                .contentType(MediaType.APPLICATION_JSON)
                .extract()
                .as(ErrorResponse.class);
        assertEquals("postRequest.request.tipo: must not be empty", response.getMessage());
    }
    @Test
    void archetype401() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setMensaje("test");
        ResponseDTO respuesta = new ResponseDTO();
        respuesta.setMensaje("test");
        Mockito.when(processRequestPort.process(any(RequestDTO.class))).thenReturn(respuesta);

        ErrorResponse response = given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestDTO)
                .when().post("/scaffold")
                .then()
                .statusCode(401)
                .contentType(MediaType.APPLICATION_JSON)
                .extract()
                .as(ErrorResponse.class);
        assertEquals("Credenciales invalidas", response.getMessage());
    }

}
