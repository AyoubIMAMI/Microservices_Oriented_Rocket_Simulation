package fr.teama.missionservice.interfaces;

import fr.teama.missionservice.exceptions.*;
import org.springframework.http.ResponseEntity;

public interface IMissionManager {
    ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException, PayloadServiceUnavailableException, TelemetryServiceUnavailableException;
    void missionFailed() throws RocketHardwareServiceUnavailableException;
    void missionSuccess() throws RocketHardwareServiceUnavailableException;
}
