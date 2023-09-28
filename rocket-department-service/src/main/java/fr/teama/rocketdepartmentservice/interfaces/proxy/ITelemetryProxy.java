package fr.teama.rocketdepartmentservice.interfaces.proxy;

import fr.teama.rocketdepartmentservice.connectors.externalDTO.TrackingFieldDTO;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;

public interface ITelemetryProxy {
    void askWhenEventHappens(TrackingFieldDTO fieldToTrack, Double data, String service, String routeToNotify, String log)
            throws TelemetryServiceUnavailableException;

}
