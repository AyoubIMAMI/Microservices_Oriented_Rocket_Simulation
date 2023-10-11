package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.missionservice.models.RocketStates;

public interface ITelemetryProxy {

    void changeRocketName(String rocketName) throws TelemetryServiceUnavailableException;

    void gettingNotifyInCaseOfRocketAnomaly(RocketStates rocketState) throws TelemetryServiceUnavailableException;

    void resetTrackings() throws TelemetryServiceUnavailableException;
}
