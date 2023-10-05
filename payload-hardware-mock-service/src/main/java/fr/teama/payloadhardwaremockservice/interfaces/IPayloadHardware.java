package fr.teama.payloadhardwaremockservice.interfaces;

import fr.teama.payloadhardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadhardwaremockservice.models.Position;

public interface IPayloadHardware {
    void startOrbitalPosDispatch(Position position) throws TelemetryServiceUnavailableException, TelemetryServiceUnavailableException;

    void stopOrbitalPosDispatch();
}
