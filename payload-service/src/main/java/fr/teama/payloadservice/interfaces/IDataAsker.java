package fr.teama.payloadservice.interfaces;

import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IDataAsker {

    ResponseEntity<String> askOrbitToTelemetry() throws TelemetryServiceUnavailableException;
}
