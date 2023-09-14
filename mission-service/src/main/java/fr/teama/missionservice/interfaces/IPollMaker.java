package fr.teama.missionservice.interfaces;

import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IPollMaker {
    ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException;
}
