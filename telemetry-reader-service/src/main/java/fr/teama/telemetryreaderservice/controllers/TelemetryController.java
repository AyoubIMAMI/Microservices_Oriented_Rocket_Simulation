package fr.teama.telemetryreaderservice.controllers;

import fr.teama.telemetryreaderservice.repository.RocketDataRepository;
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
        public static final String BASE_URI = "/api/telemetry";

    @Autowired
    private RocketDataRepository rocketDataRepository;

    @GetMapping("/service-status")
    public ResponseEntity<String> telemetryStatus() {
        return ResponseEntity.ok().body(rocketDataRepository.findAll().toString());
    }

}
