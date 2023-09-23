package fr.teama.rocketservice.interfaces.proxy;

import fr.teama.rocketservice.exceptions.TelemetryServiceUnavailableException;

public interface ITelemetryProxy {
    void gettingNotifyWhenFuelIsEmpty() throws TelemetryServiceUnavailableException;
}
