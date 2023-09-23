package fr.teama.rocketservice.components;

import fr.teama.rocketservice.interfaces.ILoggerComponent;
import fr.teama.rocketservice.interfaces.IRocketAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RocketAnalyzer implements IRocketAnalyzer {
    @Autowired
    ILoggerComponent logger;

    @Override
    public ResponseEntity<String> getRocketStatus() {
        logger.logInfo("Rocket status : GO");
        return ResponseEntity.ok().body("GO");
    }
}
