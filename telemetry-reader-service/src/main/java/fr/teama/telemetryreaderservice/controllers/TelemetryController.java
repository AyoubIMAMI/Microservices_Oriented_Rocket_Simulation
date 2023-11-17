package fr.teama.telemetryreaderservice.controllers;

import fr.teama.telemetryreaderservice.repository.RobotDataRepository;
import fr.teama.telemetryreaderservice.repository.RocketDataRepository;
import fr.teama.telemetryreaderservice.repository.StageDataRepository;
import fr.teama.telemetryreaderservice.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = TelemetryController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class TelemetryController {
        public static final String BASE_URI = "/api/telemetry-reader";

    @Autowired
    private RocketDataRepository rocketDataRepository;

    @Autowired
    private RobotDataRepository robotDataRepository;

    @Autowired
    private StageDataRepository stageDataRepository;

    @Autowired
    private TrackingRepository trackingDataRepository;

    @GetMapping("/rocket-data")
    public ResponseEntity<String> rocketData() {
        return ResponseEntity.ok().body(rocketDataRepository.findAll().toString());
    }
    @GetMapping("/robot-data")
    public ResponseEntity<String> robotData() {
        return ResponseEntity.ok().body(robotDataRepository.findAll().toString());
    }
    @GetMapping("/stage-data")
    public ResponseEntity<String> stageData() {
        return ResponseEntity.ok().body(stageDataRepository.findAll().toString());
    }

}
