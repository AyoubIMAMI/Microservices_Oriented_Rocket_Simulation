package fr.teama.payloadhadwaremockservice.interfaces;

import fr.teama.payloadhadwaremockservice.exceptions.TelemetryServiceUnavailableException;

public interface IHardware {
    void startPosDispatch() throws TelemetryServiceUnavailableException, TelemetryServiceUnavailableException;

    void stopPosDispatch();
}
