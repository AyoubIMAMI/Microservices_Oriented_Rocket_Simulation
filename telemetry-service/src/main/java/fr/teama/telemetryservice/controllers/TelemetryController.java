package fr.teama.telemetryservice.controllers;

import fr.teama.telemetryservice.controllers.dto.*;
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
