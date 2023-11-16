package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.TelemetryServiceUnavailableException;

public interface ITelemetryProxy {

    void changeRocketName(String rocketName) throws TelemetryServiceUnavailableException;

    void resetTrackings() throws TelemetryServiceUnavailableException;
}
