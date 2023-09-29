package fr.teama.rocketdepartmentservice.interfaces;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;

public interface IRocketAction {
    void stageRocket() throws RocketHardwareServiceUnavailableException;

    void launchRocket() throws TelemetryServiceUnavailableException, RocketHardwareServiceUnavailableException;

    void slowDownRocket() throws RocketHardwareServiceUnavailableException;

    void activeStage() throws RocketHardwareServiceUnavailableException;
}
