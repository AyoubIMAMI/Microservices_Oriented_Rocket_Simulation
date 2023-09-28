package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.TelemetryServiceUnavailableException;

public interface ITelemetryProxy {
    void gettingNotifyInCaseOfRocketAnomaly() throws TelemetryServiceUnavailableException;
}
