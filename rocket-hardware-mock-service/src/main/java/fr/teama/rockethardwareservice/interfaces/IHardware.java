package fr.teama.rockethardwareservice.interfaces;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;

public interface IHardware {
    void startLogging() throws TelemetryServiceUnavailableException;

    void stopLogging();

    void slowDown();

    void speedUp();
}
