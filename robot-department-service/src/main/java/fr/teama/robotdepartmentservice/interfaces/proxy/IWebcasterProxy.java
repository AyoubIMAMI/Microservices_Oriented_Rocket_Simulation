package fr.teama.robotdepartmentservice.interfaces.proxy;


import fr.teama.robotdepartmentservice.exceptions.WebcasterServiceUnavailableException;

public interface IWebcasterProxy {

    void warnWebcaster(String logs) throws WebcasterServiceUnavailableException;
}
