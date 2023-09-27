package fr.teama.missionservice.controllers;

import fr.teama.missionservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.IMissionManager;
import fr.teama.missionservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = MissionController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class MissionController {
    public static final String BASE_URI = "/api/mission";

    @Autowired
    private IMissionManager missionManager;
    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @GetMapping("/status")
    public ResponseEntity<String> missionStatus() {
        return ResponseEntity.ok().body("Mission controller started");
    }

    @PostMapping("/start")
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException, PayloadServiceUnavailableException {
        LoggerHelper.logInfo("Request received to start the mission");
        return missionManager.startMission();
    }
    @PostMapping("/success")
    public ResponseEntity<String> endMission() throws RocketHardwareServiceUnavailableException {
        LoggerHelper.logInfo("The mission has succeed");
        this.rocketHardwareProxy.stopLogging();
        return ResponseEntity.ok().body("OK");
    }
}
