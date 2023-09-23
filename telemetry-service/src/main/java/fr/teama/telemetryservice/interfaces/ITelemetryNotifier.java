package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.models.Notification;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;

public interface ITelemetryNotifier {

    void trackingNotify(Notification notification, String serviceToBeNotified);

    void changeInData(RocketData rocketData) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException;


}
