package fr.teama.rocketservice.controllers;

import fr.teama.rocketservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketservice.interfaces.IDataAsker;
import fr.teama.rocketservice.interfaces.IRocketAnalyzer;
import fr.teama.rocketservice.interfaces.RocketSplitter;
import fr.teama.rocketservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = RocketController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class RocketController {
    public static final String BASE_URI = "/api/rocket";

    @Autowired
    private IRocketAnalyzer rocketAnalyzer;

    @Autowired
    private RocketSplitter rocketSplitter;

    @Autowired
    private IDataAsker dataAsker;

    @GetMapping("/status")
    public ResponseEntity<String> getRocketStatus() {
        return rocketAnalyzer.getRocketStatus();
    }

    @PostMapping("/launch")
    public ResponseEntity<String> startRocket() {
        System.out.println("Rocket launched");
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/wait-for-stage")
    public ResponseEntity<String> waitEmptyFuelForStageTheRocket() throws TelemetryServiceUnavailableException {
        dataAsker.waitEmptyFuelForStageTheRocket();
        return ResponseEntity.ok().body("Ok, wait for telemetry notification");
    }

    @PostMapping("/stage")
    public ResponseEntity<String> stageRocket() {
        rocketSplitter.stageRocket();
        return ResponseEntity.ok().body("OK");
    }
}
