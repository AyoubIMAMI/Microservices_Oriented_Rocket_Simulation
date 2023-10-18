package fr.teama.telemetryservice.interfaces.proxy;

import fr.teama.telemetryservice.exceptions.MissionServiceUnavailableException;

public interface IMissionProxy {
    public void specificStatusDetected(String routeToNotify) throws MissionServiceUnavailableException;
}
