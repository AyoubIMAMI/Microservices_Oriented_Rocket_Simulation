package fr.teama.payloadservice.interfaces;

import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IPayloadReleaser {

    ResponseEntity<String> dropPayload() throws TelemetryServiceUnavailableException, PayloadHardwareServiceUnavaibleException;
}
