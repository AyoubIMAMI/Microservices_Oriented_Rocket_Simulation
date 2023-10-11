package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.missionservice.models.RocketStates;

public interface ITelemetryProxy {
    void gettingNotifyInCaseOfRocketAnomaly(RocketStates rocketState) throws TelemetryServiceUnavailableException;
}
