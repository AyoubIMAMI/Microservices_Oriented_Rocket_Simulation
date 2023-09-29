package fr.teama.rockethardwareservice.controllers;

import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.IRocketHardware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@CrossOrigin
@RequestMapping(path = RocketHardwareController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class RocketHardwareController {

    public static final String BASE_URI = "/api/rocket-hardware";

    @Autowired
    private IRocketHardware rocketHardware;

    @PostMapping("/start-logging")
    public ResponseEntity<String> startLogging() {
        LoggerHelper.logInfo("Request received to start logging");
        // Create an ExecutorService with a fixed thread pool size
        ExecutorService executorService = Executors.newFixedThreadPool(1); // You can adjust the pool size as needed

        // Submit the hardware.startLogging() task to the executor
        executorService.submit(() -> {
            try {
                rocketHardware.startLogging();
            } catch (TelemetryServiceUnavailableException e) {
                LoggerHelper.logError(e.toString());
            }
        });

        // Shutdown the executor when it's no longer needed
        executorService.shutdown();

        return ResponseEntity.status(HttpStatus.OK).body("Logging started successfully");
    }

    @PostMapping("/stop-logging")
    public ResponseEntity<String> stopLogging() {
        LoggerHelper.logInfo("Request received to stop logging");
        rocketHardware.stopLogging();
        return ResponseEntity.status(HttpStatus.OK).body("Logging stopped successfully");
    }

    @PostMapping("/sabotaging")
    public ResponseEntity<String> sabotagingTheRocket() {
        LoggerHelper.logWarn("Elune Mars from SpaceY comes to sabotage the rocket :(");
        rocketHardware.sabotageTheRocket();
        return ResponseEntity.ok("Sabotaging successful");
    }

    @PostMapping("/destroy")
    public ResponseEntity<String> rocketDestruction() {
        LoggerHelper.logInfo("Request received for destroy the rocket");
        rocketHardware.destroyHardware();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/stage")
    public ResponseEntity<String> stageRocket() throws StageHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Request received for stage the rocket");
        rocketHardware.stageRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/activate-stage")
    public ResponseEntity<String> activeStageRocket() {
        LoggerHelper.logInfo("Request received for activate lowest stage of the rocket");
        rocketHardware.activateStageRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/deactivate-stage")
    public ResponseEntity<String> deactiveStageRocket() {
        LoggerHelper.logInfo("Request received for deactivate lowest stage of the rocket");
        rocketHardware.deactivateStageRocket();
        return ResponseEntity.ok().body("OK");
    }
}
