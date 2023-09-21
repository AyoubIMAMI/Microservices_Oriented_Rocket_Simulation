package fr.teama.payloadservice.interfaces.proxy;

import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface ITelemetryProxy {
    ResponseEntity<String> missionStartNotify() throws TelemetryServiceUnavailableException;
}
