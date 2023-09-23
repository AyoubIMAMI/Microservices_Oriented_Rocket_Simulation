package fr.teama.rocketdepartmentservice.interfaces.proxy;

import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;

public interface ITelemetryProxy {
    void gettingNotifyWhenFuelIsEmpty() throws TelemetryServiceUnavailableException;
}
