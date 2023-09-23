package fr.teama.payloadservice.interfaces.proxy;

import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IMissionProxy {
    ResponseEntity<String> missionSuccessNotify() throws TelemetryServiceUnavailableException;
}
