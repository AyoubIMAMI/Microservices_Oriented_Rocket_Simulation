package fr.teama.rocketdepartmentservice.interfaces.proxy;

import fr.teama.rocketdepartmentservice.exceptions.WebcasterServiceUnavailableException;

public interface IWebcasterProxy {

    void warnWebcaster(String logs) throws WebcasterServiceUnavailableException;
}
