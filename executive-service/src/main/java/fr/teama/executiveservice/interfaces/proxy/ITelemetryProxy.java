package fr.teama.executiveservice.interfaces.proxy;

import fr.teama.executiveservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface ITelemetryProxy {
    ResponseEntity<String> missionStartNotify() throws TelemetryServiceUnavailableException;
}
