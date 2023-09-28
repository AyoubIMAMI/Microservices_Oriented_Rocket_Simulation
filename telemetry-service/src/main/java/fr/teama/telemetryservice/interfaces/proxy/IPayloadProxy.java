package fr.teama.telemetryservice.interfaces.proxy;


import fr.teama.telemetryservice.controllers.dto.PayloadDataDTO;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IPayloadProxy {

    void heightReached() throws PayloadServiceUnavailableException;


    ResponseEntity<PayloadDataDTO> sendData(PayloadDataDTO payloadDataDTO) throws PayloadServiceUnavailableException;
}
