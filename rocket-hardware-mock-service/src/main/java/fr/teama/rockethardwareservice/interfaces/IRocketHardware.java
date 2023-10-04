package fr.teama.rockethardwareservice.interfaces;

import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.models.RocketData;

public interface IRocketHardware {
    void startLogging() throws TelemetryServiceUnavailableException;

    void stopLogging();

    void sabotageTheRocket();

    void destroyHardware();

    void stageRocket() throws StageHardwareServiceUnavailableException;

    void activateStageRocket();

    void deactivateStageRocket();

    RocketData getRocketData();

    void fuelingTheRocket();
}
