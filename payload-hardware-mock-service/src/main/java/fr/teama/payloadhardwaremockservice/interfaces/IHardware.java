package fr.teama.payloadhardwaremockservice.interfaces;

import fr.teama.payloadhardwaremockservice.exceptions.TelemetryServiceUnavailableException;

public interface IHardware {
    void startOrbitalPosDispatch() throws TelemetryServiceUnavailableException, TelemetryServiceUnavailableException;

    void stopOrbitalPosDispatch();
}
