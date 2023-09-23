package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAnalyzer;
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
