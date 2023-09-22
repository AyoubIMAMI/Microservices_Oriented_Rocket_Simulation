package fr.teama.telemetryservice.controllers;

import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.TrackingDTO;
import fr.teama.telemetryservice.entities.Notification;
import fr.teama.telemetryservice.entities.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.interfaces.ILoggerComponent;
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
    private ITelemetryNotifier telemetryAnalyzer;

    @Autowired
    private DataSaver dataSaver;
    @Autowired
    ILoggerComponent logger;

    @PostMapping("/tracking")
    public ResponseEntity<String> whenTelemetryReachConditions(@RequestBody TrackingDTO trackingDTO) {
        logger.logInfo("New tracking request received");
        Notification notification=new Notification(trackingDTO.getServiceToBeNotified());
        trackingDTO.getData().forEach(data-> {
            if (data.getFieldToTrack().equals("height"))
                notification.setHeight(data.getData());
            else if (data.getFieldToTrack().equals("fuel"))
                notification.setFuel(data.getData());
        });
        telemetryAnalyzer.trackingNotify(notification,trackingDTO.getServiceToBeNotified());
        return ResponseEntity.ok().body("Tracking condition saved");
    }


    @PostMapping("/send-data")
    public ResponseEntity<String> saveDataNewData(@RequestBody RocketDataDTO rocket) throws RocketStageServiceUnavailableException, PayloadServiceUnavailableException {
        logger.logInfo("Saving data from rocket hardware : " + rocket.toString());
        return this.dataSaver.saveData(new RocketData(rocket));
    }
}
