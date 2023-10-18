package fr.teama.rockethardwareservice.controllers;

import fr.teama.rockethardwareservice.controllers.dto.RocketDataDTO;
import fr.teama.rockethardwareservice.controllers.dto.StageDataDTO;
import fr.teama.rockethardwareservice.exceptions.MissionServiceUnvailableException;
import fr.teama.rockethardwareservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.IRocketHardware;
import fr.teama.rockethardwareservice.models.RocketData;
import fr.teama.rockethardwareservice.models.StageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
            } catch (TelemetryServiceUnavailableException | MissionServiceUnvailableException e) {
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

    @GetMapping(path = "/preparation", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RocketDataDTO> getRocketData() {
        LoggerHelper.logInfo("Request received to send stages fuel level");
        RocketData rocketData = rocketHardware.getRocketData();

        List<StageDataDTO> stageDataDTOList = getStageDataDTOS(rocketData);

        RocketDataDTO rocketDataDTO = new RocketDataDTO();
        rocketDataDTO.setStatus(rocketData.getStatus());
        rocketDataDTO.setStages(stageDataDTOList);

        return ResponseEntity.ok().body(rocketDataDTO);
    }

    private static List<StageDataDTO> getStageDataDTOS(RocketData rocketData) {
        List<StageDataDTO> stageDataDTOList = new ArrayList<>();
        List<StageData> stageDataList = rocketData.getStages();

        for (StageData stageData : stageDataList) {
            StageDataDTO stageDataDTO = new StageDataDTO();
            stageDataDTO.setStageLevel(stageData.getStageLevel());
            stageDataDTO.setFuel(stageData.getFuel());
            stageDataDTOList.add(stageDataDTO);
        }
        return stageDataDTOList;
    }

    @PostMapping("/fueling")
    public ResponseEntity<String> fuelingTheRocket() {
        rocketHardware.fuelingTheRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/sabotaging")
    public ResponseEntity<String> sabotagingTheRocket() {
        LoggerHelper.logWarn("Elune Mars from SpaceY comes to sabotage the rocket :(");
        rocketHardware.sabotageTheRocket();
        return ResponseEntity.ok("Sabotaging successful");
    }

    @PostMapping("/dangerous-sabotaging")
    public ResponseEntity<String> criticalSabotagingOfTheRocket() {
        LoggerHelper.logWarn("Palpatooine comes to redirect the rocket and destroy the earth");
        rocketHardware.criticalSabotagingOfTheRocket();
        return ResponseEntity.ok("Sabotaging successful");
    }

    @PostMapping("/pressure-anomaly")
    public ResponseEntity<String> pressureAnomalyOnTheRocket() {
        LoggerHelper.logWarn("The rocket hurt a little asteroid");
        rocketHardware.pressureAnomalyOnTheRocket();
        return ResponseEntity.ok("Pressure anomaly successful");
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
    public ResponseEntity<String> activateStageRocket() {
        LoggerHelper.logInfo("Request received for activate lowest stage of the rocket");
        rocketHardware.activateStageRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/deactivate-stage")
    public ResponseEntity<String> deactivateStageRocket() {
        LoggerHelper.logInfo("Request received for deactivate lowest stage of the rocket");
        rocketHardware.deactivateStageRocket();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/drop-payload")
    public ResponseEntity<String> dropPayload() throws PayloadHardwareServiceUnavaibleException {
        LoggerHelper.logInfo("Request received for drop the payload");
        rocketHardware.dropPayload();
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping("/fairing")
    public ResponseEntity<String> fairing() {
        LoggerHelper.logInfo("Request received to deploy the fairing");
        rocketHardware.fairing();
        return ResponseEntity.ok().body("OK");
    }
}
