package fr.teama.telemetryservice.interfaces;

import org.springframework.http.ResponseEntity;

public interface ITelemetryAnalyzer {

    ResponseEntity<String> getTelemetryStatus();
}
