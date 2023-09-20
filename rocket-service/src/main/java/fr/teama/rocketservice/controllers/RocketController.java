package fr.teama.rocketservice.controllers;

import fr.teama.rocketservice.components.LoggerComponent;
import fr.teama.rocketservice.interfaces.IRocketAnalyzer;
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
    private LoggerComponent logger;

    @GetMapping("/status")
    public ResponseEntity<String> getRocketStatus() {
        return rocketAnalyzer.getRocketStatus();
    }

    @PostMapping("/launch")
    public ResponseEntity<String> startRocket() {
        logger.logInfo("Rocket launched");
        return ResponseEntity.ok().body("OK");
    }
}
