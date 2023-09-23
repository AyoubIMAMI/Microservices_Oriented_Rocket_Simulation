package fr.teama.rocketservice.components;

import fr.teama.rocketservice.helpers.LoggerHelper;
import fr.teama.rocketservice.interfaces.IRocketAnalyzer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RocketAnalyzer implements IRocketAnalyzer {

    @Override
    public ResponseEntity<String> getRocketStatus() {
        LoggerHelper.logInfo("Rocket status : GO");
        return ResponseEntity.ok().body("GO");
    }
}
