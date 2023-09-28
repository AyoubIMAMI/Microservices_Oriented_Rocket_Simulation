package fr.teama.rocketdepartmentservice.interfaces.proxy;


import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;

public interface IRocketHardwareProxy {

    void stageRocket() throws RocketHardwareServiceUnavailableException;

    void activateCurrentStage() throws RocketHardwareServiceUnavailableException;
}
