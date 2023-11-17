package fr.teama.rocketdepartmentservice.interfaces;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;

public interface IRocketAction {
    void stageRocket() throws RocketHardwareServiceUnavailableException;

    void launchRocket() throws RocketHardwareServiceUnavailableException, InterruptedException;

    void slowDownRocket() throws RocketHardwareServiceUnavailableException;

    void activeStage() throws RocketHardwareServiceUnavailableException;

    void fairing() throws RocketHardwareServiceUnavailableException;
}
