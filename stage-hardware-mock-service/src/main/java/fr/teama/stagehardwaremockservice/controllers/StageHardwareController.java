package fr.teama.stagehardwaremockservice.controllers;

import fr.teama.stagehardwaremockservice.controllers.dto.StageFullStateDTO;
import fr.teama.stagehardwaremockservice.helpers.LoggerHelper;
import fr.teama.stagehardwaremockservice.interfaces.IStageHardware;
import fr.teama.stagehardwaremockservice.models.StageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@CrossOrigin
@RequestMapping(path = StageHardwareController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class StageHardwareController {

    public static final String BASE_URI = "/api/stage-hardware";

    @Autowired
    IStageHardware stageHardware;

    @PostMapping("/start-logging")
    public ResponseEntity<String> startLogging(@RequestBody StageFullStateDTO fullStageDTO) {
        LoggerHelper.logInfo("Request received to start logging for stage " + fullStageDTO.getStageData().getStageLevel());

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.submit(() -> stageHardware.startLogging(new StageData(fullStageDTO)));

        executorService.shutdown();

        return ResponseEntity.ok("Logging started successfully");
    }
}
