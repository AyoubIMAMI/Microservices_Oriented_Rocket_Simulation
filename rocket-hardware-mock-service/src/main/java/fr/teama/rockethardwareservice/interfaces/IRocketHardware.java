package fr.teama.rockethardwareservice.interfaces;

import fr.teama.rockethardwareservice.exceptions.MissionServiceUnvailableException;
import fr.teama.rockethardwareservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.exceptions.RobotHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import fr.teama.rockethardwareservice.models.RocketData;

public interface IRocketHardware {
    void startLogging() throws MissionServiceUnvailableException;

    void stopLogging();

    void sabotageTheRocket();

    void destroyHardware();

    void stageRocket() throws StageHardwareServiceUnavailableException;

    void activateStageRocket();

    void deactivateStageRocket();

    RocketData getRocketData();

    void fuelingTheRocket();

    void dropPayload() throws PayloadHardwareServiceUnavaibleException;

    void fairing();

    void pressureAnomalyOnTheRocket();

    void dropRobot() throws RobotHardwareServiceUnavaibleException;

    void criticalSabotagingOfTheRocket();
}
