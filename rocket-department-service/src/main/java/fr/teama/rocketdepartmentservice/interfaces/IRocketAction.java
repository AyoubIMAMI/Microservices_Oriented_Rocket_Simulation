package fr.teama.rocketdepartmentservice.interfaces;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.WebcasterServiceUnavailableException;

public interface IRocketAction {
    void stageRocket() throws RocketHardwareServiceUnavailableException;

    void launchRocket() throws TelemetryServiceUnavailableException, RocketHardwareServiceUnavailableException, InterruptedException, WebcasterServiceUnavailableException;

    void slowDownRocket() throws RocketHardwareServiceUnavailableException;

    void activeStage() throws RocketHardwareServiceUnavailableException;

    void fairing() throws RocketHardwareServiceUnavailableException;
}
