package fr.teama.rocketdepartmentservice.interfaces.proxy;

public interface IRocketHardwareProxy {

    void stageRocket() throws RocketHardwareServiceUnavailableException;

    void activateCurrentStage() throws RocketHardwareServiceUnavailableException;
}
