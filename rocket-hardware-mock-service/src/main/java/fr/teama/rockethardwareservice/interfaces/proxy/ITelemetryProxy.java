package fr.teama.rockethardwareservice.interfaces.proxy;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.models.Rocket;

public interface ITelemetryProxy {

    void sendRocketData(Rocket rocket) throws TelemetryServiceUnavailableException;
}
