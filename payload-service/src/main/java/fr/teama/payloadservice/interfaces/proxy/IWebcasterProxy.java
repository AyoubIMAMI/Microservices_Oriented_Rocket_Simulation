package fr.teama.payloadservice.interfaces.proxy;

import fr.teama.payloadservice.exceptions.WebcasterServiceUnavailableException;

public interface IWebcasterProxy {

    void warnWebcaster(String logs) throws WebcasterServiceUnavailableException;
}
