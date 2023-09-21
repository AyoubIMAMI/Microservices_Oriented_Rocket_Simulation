package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.entities.Notification;
import fr.teama.telemetryservice.entities.RocketData;

public interface ITelemetryNotifier {

    void trackingNotify(Notification notification, String serviceToBeNotified);

    void changeInData(RocketData rocketData);


}
