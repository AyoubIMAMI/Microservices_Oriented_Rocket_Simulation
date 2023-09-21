package fr.teama.rocketservice.interfaces;

import fr.teama.rocketservice.exceptions.TelemetryServiceUnavailableException;

public interface IDataAsker {
    void waitEmptyFuelForStageTheRocket() throws TelemetryServiceUnavailableException;
}
