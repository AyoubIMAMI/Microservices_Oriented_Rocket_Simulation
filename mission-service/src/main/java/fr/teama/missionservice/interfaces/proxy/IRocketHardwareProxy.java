package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;

public interface IRocketHardwareProxy {

    void startLogging() throws RocketHardwareServiceUnavailableException;
    void stopLogging() throws RocketHardwareServiceUnavailableException;

}
