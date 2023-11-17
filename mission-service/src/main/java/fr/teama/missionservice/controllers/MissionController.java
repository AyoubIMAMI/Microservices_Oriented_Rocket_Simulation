package fr.teama.missionservice.controllers;

import fr.teama.missionservice.services.KafkaProducerService;
import fr.teama.missionservice.exceptions.*;
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
    @Autowired
    KafkaProducerService kafkaProducerService;

    @PostMapping("/start")
    public ResponseEntity<String> startMission(@RequestBody String rocketName) throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException , TelemetryServiceUnavailableException, LogsServiceUnavailableException, NotifyStateNotSupportedException {
        LoggerHelper.logInfo("Request received to start the mission for rocket " + rocketName);
        return missionManager.startMission(rocketName);
    }
    @PostMapping("/success")
    public ResponseEntity<String> endMission() throws RocketHardwareServiceUnavailableException, LogsServiceUnavailableException {
        missionManager.missionSuccess();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/sendMessageToWeather")
    public ResponseEntity<String> sendMessage() {
        kafkaProducerService.sendMessage("test");
        return ResponseEntity.ok("message sent");
    }

    @PostMapping("/rocket-critical-anomaly")
    public ResponseEntity<String> rocketSelfDestroyedDueToCriticalAnomaly() throws RocketHardwareServiceUnavailableException, LogsServiceUnavailableException {
        missionManager.missionFailed();
        return ResponseEntity.ok("Receive information about the critical anomaly");
    }
}
