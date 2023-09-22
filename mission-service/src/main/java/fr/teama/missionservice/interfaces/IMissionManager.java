package fr.teama.missionservice.interfaces;

import fr.teama.missionservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IMissionManager {
    ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException, PayloadServiceUnavailableException;
}
