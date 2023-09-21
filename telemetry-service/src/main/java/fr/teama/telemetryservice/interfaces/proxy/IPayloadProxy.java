package fr.teama.telemetryservice.interfaces.proxy;


import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;

public interface IPayloadProxy {

    void heightReached() throws PayloadServiceUnavailableException;


}
