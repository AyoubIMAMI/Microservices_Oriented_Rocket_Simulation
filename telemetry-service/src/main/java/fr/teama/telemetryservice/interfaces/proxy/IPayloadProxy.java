package fr.teama.telemetryservice.interfaces.proxy;


import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.models.PayloadData;
import org.springframework.http.ResponseEntity;

public interface IPayloadProxy {

    void heightReached() throws PayloadServiceUnavailableException;


    ResponseEntity<String> sendData(PayloadData payloadData) throws PayloadServiceUnavailableException;
}
