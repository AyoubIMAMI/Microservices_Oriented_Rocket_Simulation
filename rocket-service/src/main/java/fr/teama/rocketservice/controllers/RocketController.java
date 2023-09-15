package fr.teama.rocketservice.controllers;

import fr.teama.rocketservice.interfaces.IRocketAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RocketController {
    public static final String BASE_URI = "/api/rocket";

    @Autowired
    private IRocketAnalyzer rocketAnalyzer;

    @GetMapping(path = BASE_URI + "/status")
    public ResponseEntity<String> getRocketStatus() {
        return rocketAnalyzer.getRocketStatus();
    }

    @PostMapping(path = BASE_URI + "/launch")
    public ResponseEntity<String> startRocket() {
        System.out.println("GO");
        return ResponseEntity.ok().body("OK");
    }
}
