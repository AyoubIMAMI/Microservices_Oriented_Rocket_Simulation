package fr.teama.missionservice.interfaces.proxy;

import fr.teama.missionservice.exceptions.WebcasterServiceUnavailableException;

public interface IWebcasterProxy {

    void warnWebcaster(String logs) throws WebcasterServiceUnavailableException;
}
