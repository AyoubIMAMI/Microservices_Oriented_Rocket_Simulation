package fr.teama.telemetryservice.controllers;

import fr.teama.telemetryservice.controllers.dto.TrackingDTO;
import fr.teama.telemetryservice.entities.Notification;
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
    private ITelemetryNotifier telemetryAnalyzer;

    @PostMapping("/tracking")
    public ResponseEntity<String> whenTelemetryReachAltitude(TrackingDTO trackingDTO) {
        Notification notification=new Notification(trackingDTO.getServiceToBeNotified());

        trackingDTO.getData().forEach(data-> {
            if (data.getFieldToTrack().equals("height"))
                notification.setHeight(data.getData());
            else if (data.getFieldToTrack().equals("fuel"))
                notification.setFuel(data.getData());
        });

        return telemetryAnalyzer.trackingNotify(notification,trackingDTO.getServiceToBeNotified());
    }


    @PostMapping("/send-data")
    public ResponseEntity<String> saveDataNewData() {
        return ResponseEntity.ok().body("saved");
    }
}
