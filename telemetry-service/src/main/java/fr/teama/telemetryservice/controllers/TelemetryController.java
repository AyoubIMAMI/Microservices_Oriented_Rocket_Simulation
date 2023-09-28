package fr.teama.telemetryservice.controllers;

import fr.teama.telemetryservice.connectors.PayloadProxy;
import fr.teama.telemetryservice.controllers.dto.PayloadDataDTO;
import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.TrackingDTO;
import fr.teama.telemetryservice.interfaces.proxy.IPayloadProxy;
import fr.teama.telemetryservice.models.Tracking;
import fr.teama.telemetryservice.exceptions.MissionServiceUnavailableException;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = TelemetryController.BASE_URI, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class TelemetryController {
    public static final String BASE_URI = "/api/telemetry";

    @Autowired
    private ITelemetryNotifier telemetryNotifier;

    @Autowired
    private IPayloadProxy payloadProxy;

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

    @PostMapping("/send-rocket-data")
    public ResponseEntity<String> saveDataNewData(@RequestBody RocketDataDTO rocket) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException, MissionServiceUnavailableException {
        LoggerHelper.logInfo("Saving data from rocket hardware");
        return this.dataSaver.saveData(new RocketData(rocket));
    }

    @PostMapping("/send-payload-data")
    public ResponseEntity<String> transferPayloadData(@RequestBody PayloadDataDTO payloadDataDTO) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException {
        return this.payloadProxy.sendData(payloadDataDTO);
    }
}
