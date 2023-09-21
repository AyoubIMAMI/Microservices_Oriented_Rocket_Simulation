package fr.teama.telemetryservice.interfaces.proxy;

import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;

public interface IRocketStageProxy {

    void fuelLevelReached() throws PayloadServiceUnavailableException;

}
