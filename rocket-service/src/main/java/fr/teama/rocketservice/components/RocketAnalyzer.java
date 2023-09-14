package fr.teama.rocketservice.components;

import fr.teama.rocketservice.interfaces.IRocketAnalyzer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RocketAnalyzer implements IRocketAnalyzer {

    @Override
    public ResponseEntity<String> getRocketStatus() {
        return ResponseEntity.ok().body("GO");
    }
}
