package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.interfaces.ITelemetryAnalyzer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TelemetryAnalyzer implements ITelemetryAnalyzer {

    @Override
    public ResponseEntity<String> getTelemetryStatus() {
        return ResponseEntity.ok().body("GO");
    }
}
