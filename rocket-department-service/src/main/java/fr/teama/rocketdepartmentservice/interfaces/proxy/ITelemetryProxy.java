package fr.teama.rocketdepartmentservice.interfaces.proxy;

import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;

public interface ITelemetryProxy {
    void askWhenEventHappens(String fieldToTrack, Double data, String service, String log)
            throws TelemetryServiceUnavailableException;

}
