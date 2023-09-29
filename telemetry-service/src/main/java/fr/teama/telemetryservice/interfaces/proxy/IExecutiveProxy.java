package fr.teama.telemetryservice.interfaces.proxy;


import fr.teama.telemetryservice.exceptions.ExecutiveServiceUnavailableException;

public interface IExecutiveProxy {

    void notifyStageLanded() throws ExecutiveServiceUnavailableException;
}
