package fr.teama.rockethardwareservice.interfaces;

import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;

public interface IRocketHardware {
    void startLogging() throws TelemetryServiceUnavailableException;

    void stopLogging();

    void slowDown();

    void speedUp();

    void sabotageTheRocket();

    void destroyHardware();

    void stageRocket() throws StageHardwareServiceUnavailableException;

    void activateStageRocket();

    void deactivateStageRocket();
}
