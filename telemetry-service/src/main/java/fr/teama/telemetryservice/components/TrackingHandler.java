package fr.teama.telemetryservice.components;

import fr.teama.telemetryservice.entities.Notification;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TrackingHandler implements ITelemetryNotifier {

    @Override
    public ResponseEntity<String> trackingNotify(Notification notification, String serviceToBeNotified) {
        return null;
    }
}
