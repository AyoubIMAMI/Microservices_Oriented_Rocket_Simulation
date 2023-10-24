package fr.teama.robotdepartmentservice.interfaces.proxy;

import fr.teama.robotdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface ITelemetryProxy {
    ResponseEntity<String> askRocketHeight() throws TelemetryServiceUnavailableException;
    ResponseEntity<String> askRobotHeight() throws TelemetryServiceUnavailableException;
}
