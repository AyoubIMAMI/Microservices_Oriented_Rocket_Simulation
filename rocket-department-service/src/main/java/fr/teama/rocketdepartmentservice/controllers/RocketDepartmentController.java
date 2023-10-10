package fr.teama.rocketdepartmentservice.controllers;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.WebcasterServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IDataAsker;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAnalyzer;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAction;
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
    private IRocketAction rocketAction;

    @Autowired
    private IDataAsker dataAsker;

    @GetMapping("/status")
    public ResponseEntity<String> getRocketStatus() {
        LoggerHelper.logInfo("Request received to send rocket status");
        return rocketAnalyzer.getRocketStatus();
    }

    @PostMapping("/launch")
    public ResponseEntity<String> startRocket() throws TelemetryServiceUnavailableException,
            RocketHardwareServiceUnavailableException, InterruptedException, WebcasterServiceUnavailableException {
        LoggerHelper.logInfo("Request received to launch the rocket in 60 seconds");
        rocketAction.launchRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/stage")
    public ResponseEntity<String> stageRocket() throws RocketHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Notification received, fuel condition reached for staging the rocket");
        rocketAction.stageRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/enters-q")
    public ResponseEntity<String> slowDownRocket() throws RocketHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Notification received, rocket enters Max Q => slow down");
        rocketAction.slowDownRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/leaves-q")
    public ResponseEntity<String> speedUpRocket() throws RocketHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Notification received, rocket leaves Max Q => speed up");
        rocketAction.activeStage();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/fairing-altitude")
    public ResponseEntity<String> fairingAltitude() throws RocketHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Notification received, rocket reaches the orbit altitude");
        rocketAction.fairing();
        LoggerHelper.logInfo("The rocket department wants the second engine to cut-off");
        rocketAction.slowDownRocket();
        return ResponseEntity.ok().body("OK");
    }
}
