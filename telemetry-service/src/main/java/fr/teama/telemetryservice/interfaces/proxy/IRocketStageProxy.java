package fr.teama.telemetryservice.interfaces.proxy;


import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;

public interface IRocketStageProxy {

    void fuelLevelReached() throws RocketStageServiceUnavailableException;

}
