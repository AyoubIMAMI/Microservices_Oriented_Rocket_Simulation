package fr.teama.robothardwaremockservice.controllers;

import fr.teama.robothardwaremockservice.helpers.LoggerHelper;
import fr.teama.robothardwaremockservice.interfaces.IRobotHardware;
import fr.teama.robothardwaremockservice.models.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = RobotHardwareController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class RobotHardwareController {

    public static final String BASE_URI = "/api/robot-hardware";

    @Autowired
    IRobotHardware robotHardware;

    @PostMapping("/start-logging")
    public ResponseEntity<String> startLogging(@RequestBody Position position) {
        LoggerHelper.logInfo("Request received to start logging for robot");

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.submit(() -> robotHardware.startLogging(position));

        executorService.shutdown();

        return ResponseEntity.ok("Logging started successfully");
    }

    @PostMapping("/stop-logging")
    public ResponseEntity<String> stopLogging() {
        robotHardware.stopLogging();
        return ResponseEntity.ok("Logging stopped successfully");
    }

}
