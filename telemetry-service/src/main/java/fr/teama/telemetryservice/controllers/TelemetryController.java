package fr.teama.telemetryservice.controllers;

import fr.teama.telemetryservice.interfaces.ITelemetryAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = TelemetryController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class TelemetryController {
    public static final String BASE_URI = "/api/telemetry";



    @Autowired
    private ITelemetryAnalyzer telemetryAnalyzer;

    @PostMapping("/reach/altitude")
    public ResponseEntity<String> whenTelemetryReachAltitude() {
        return telemetryAnalyzer.getTelemetryStatus();
    }

    @PostMapping("/reach/fuel")
    public ResponseEntity<String> whenTelemetryReachFuel() {
        return telemetryAnalyzer.getTelemetryStatus();
    }


    @PostMapping("/sendData")
    public ResponseEntity<String> saveDataNewData() {
        return ResponseEntity.ok().body("saved");
    }
}
