package fr.teama.telemetryservice.controllers;

import fr.teama.telemetryservice.controllers.dto.*;
import fr.teama.telemetryservice.exceptions.*;
import fr.teama.telemetryservice.interfaces.DataSender;
import fr.teama.telemetryservice.models.Tracking;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = TelemetryController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class TelemetryController {
    public static final String BASE_URI = "/api/telemetry";

    @Autowired
    private ITelemetryNotifier telemetryNotifier;

    @Autowired
    private DataSaver dataSaver;

    @Autowired
    private DataSender dataSender;

    @GetMapping("/service-status")
    public ResponseEntity<String> telemetryStatus() {
        return ResponseEntity.ok().body("Service controller started");
    }

    @PostMapping("/tracking")
    public ResponseEntity<String> createTrackingNotification(@RequestBody TrackingDTO trackingDTO) {
        LoggerHelper.logInfo("New tracking request received from " + trackingDTO.getServiceToBeNotified() + " service");
        Tracking tracking = new Tracking(trackingDTO);
        return ResponseEntity.ok().body("Tracking condition saved: " + telemetryNotifier.trackingNotify(tracking));
    }

    @PostMapping("/send-rocket-data")
    public ResponseEntity<String> saveNewRocketData(@RequestBody RocketDataDTO rocketDataDTO) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Receive \u001B[33mrocket\u001B[32m hardware data: " + rocketDataDTO.toString());
        return this.dataSaver.saveRocketData(rocketDataDTO);
    }

    @PostMapping("/send-stage-data")
    public ResponseEntity<String> saveNewStageData(@RequestBody StageDataDTO stageDataDTO) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Receive \u001B[38;5;165mstage\u001B[32m " + stageDataDTO.getStageLevel() + " hardware data: " + stageDataDTO.toStringComplete());
        return this.dataSaver.saveStageData(stageDataDTO);
    }

    @PostMapping("/send-robot-data")
    public ResponseEntity<String> saveNewRobotData(@RequestBody RobotDataDTO robotDataDTO) throws RocketStageServiceUnavailableException, ExecutiveServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Receive \u001B[93mrobot\u001B[32m hardware data: " + robotDataDTO.toString());
        return this.dataSaver.saveRobotData(robotDataDTO);
    }

    @PostMapping("/send-payload-data")
    public ResponseEntity<String> transferPayloadData(@RequestBody PayloadDataDTO payloadDataDTO) throws PayloadServiceUnavailableException {
        return this.dataSender.sendPayloadData(payloadDataDTO);
    }

    @PostMapping("/reset-tracking")
    public ResponseEntity<String> resetDb() {
        return this.dataSaver.resetTracking();
    }

    @PostMapping("/rocket-name")
    public ResponseEntity<String> changeRocketName(@RequestBody String rocketName) {
        LoggerHelper.logInfo("Change rocket name to " + rocketName + " for future data");
        return this.dataSaver.changeRocketName(rocketName);
    }
}
