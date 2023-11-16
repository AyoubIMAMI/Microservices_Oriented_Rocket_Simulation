package fr.teama.payloadhardwaremockservice.controllers;

import fr.teama.payloadhardwaremockservice.helpers.LoggerHelper;
import fr.teama.payloadhardwaremockservice.interfaces.IPayloadHardware;
import fr.teama.payloadhardwaremockservice.models.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@CrossOrigin
@RequestMapping(path = PayloadHardwareController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class PayloadHardwareController {

    public static final String BASE_URI = "/api/payload-hardware";

    @Autowired
    private IPayloadHardware hardware;

    @PostMapping("/start-pos-dispatch")
    public ResponseEntity<String> startOrbitalPosDispatch(@RequestBody Position position) {
        LoggerHelper.logInfo("Request received to start orbital position dispatch");
        // Create an ExecutorService with a fixed thread pool size
        ExecutorService executorService = Executors.newFixedThreadPool(1); // You can adjust the pool size as needed

        // Submit the hardware.startLogging() task to the executor
        executorService.submit(() -> {
            try {
                hardware.startOrbitalPosDispatch(position);
            } catch (Exception e) {
                // Handle the exception as needed
                LoggerHelper.logError(e.toString());
            }
        });

        // Shutdown the executor when it's no longer needed
        executorService.shutdown();

        return ResponseEntity.status(HttpStatus.OK).body("Start orbital position dispatch");
    }

    @PostMapping("/stop-pos-dispatch")
    public ResponseEntity<String> stopOrbitalPosDispatch() {
        hardware.stopOrbitalPosDispatch();
        return ResponseEntity.status(HttpStatus.OK).body("Stop orbital position dispatch");
    }
}
