package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;

public interface IRocketProxy {

    String getRocketStatus() throws RocketServiceUnavailableException;
}
