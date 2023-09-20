package fr.teama.rockethardwareservice.interfaces.proxy;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;

public interface ITelemetryProxy {

    void postData(String data) throws TelemetryServiceUnavailableException;
}
