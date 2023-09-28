package fr.teama.rockethardwareservice.interfaces;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;

public interface IRocketHardware {
    void startLogging() throws TelemetryServiceUnavailableException;

    void stopLogging();

    void sabotageTheRocket();

    void destroyHardware();

    void stageRocket();

    void activateStageRocket();

    void deactivateStageRocket();
}
