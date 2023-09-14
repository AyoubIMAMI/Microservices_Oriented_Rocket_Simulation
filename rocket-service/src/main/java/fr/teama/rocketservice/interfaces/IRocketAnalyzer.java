package fr.teama.rocketservice.interfaces;

import org.springframework.http.ResponseEntity;

public interface IRocketAnalyzer {

    ResponseEntity<String> getRocketStatus();
}
