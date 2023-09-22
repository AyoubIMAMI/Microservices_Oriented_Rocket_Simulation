package fr.teama.missionservice.controllers;

import fr.teama.missionservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import fr.teama.missionservice.interfaces.ILoggerComponent;
import fr.teama.missionservice.interfaces.IMissionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = MissionController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class MissionController {
    public static final String BASE_URI = "/api/mission";

    @Autowired
    private IMissionManager missionManager;
    @Autowired
    ILoggerComponent logger;

    @PostMapping("/start")
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException, PayloadServiceUnavailableException {
        logger.logInfo("Request received to start the mission");
        return missionManager.startMission();
    }
}
