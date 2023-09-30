package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;

public interface IRocketDepartmentProxy {

    String getRocketStatus() throws RocketServiceUnavailableException;

    void launchRocket() throws RocketServiceUnavailableException;
}
