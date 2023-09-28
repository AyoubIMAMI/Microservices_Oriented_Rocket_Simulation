package fr.teama.payloadhadwaremockservice.interfaces;

import fr.teama.payloadhadwaremockservice.exceptions.TelemetryServiceUnavailableException;

public interface IHardware {
    void startOrbitalPosDispatch() throws TelemetryServiceUnavailableException, TelemetryServiceUnavailableException;

    void stopOrbitalPosDispatch();
}
