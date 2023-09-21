package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.entities.Notification;
import fr.teama.telemetryservice.entities.RocketData;
import org.springframework.http.ResponseEntity;

public interface DataSaver {
    ResponseEntity<String> saveData(RocketData rocketData);
}
