package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.models.Tracking;
import fr.teama.telemetryservice.models.RocketData;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;

public interface ITelemetryNotifier {

    Tracking trackingNotify(Tracking tracking);

    void verifyRocketData(RocketData rocketData) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException;


}
