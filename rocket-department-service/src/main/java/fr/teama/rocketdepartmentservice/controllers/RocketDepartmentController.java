package fr.teama.rocketdepartmentservice.controllers;

import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IDataAsker;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAnalyzer;
import fr.teama.rocketdepartmentservice.interfaces.RocketSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = RocketDepartmentController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class RocketDepartmentController {
    public static final String BASE_URI = "/api/rocket";

    @Autowired
    private IRocketAnalyzer rocketAnalyzer;

    @Autowired
    private RocketSplitter rocketSplitter;

    @Autowired
    private IDataAsker dataAsker;

    @GetMapping("/status")
    public ResponseEntity<String> getRocketStatus() {
        return rocketAnalyzer.getRocketStatus();
    }

    @PostMapping("/launch")
    public ResponseEntity<String> startRocket() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("Rocket launched");
        dataAsker.waitEmptyFuelForStageTheRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/stage")
    public ResponseEntity<String> stageRocket() {
        rocketSplitter.stageRocket();
        return ResponseEntity.ok().body("OK");
    }
}
