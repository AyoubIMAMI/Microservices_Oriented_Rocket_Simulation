package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.entities.Notification;
import org.springframework.http.ResponseEntity;

public interface ITelemetryNotifier {

    ResponseEntity<String> trackingNotify(Notification notification, String serviceToBeNotified);
}
