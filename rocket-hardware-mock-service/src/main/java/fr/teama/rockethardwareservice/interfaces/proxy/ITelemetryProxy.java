package fr.teama.rockethardwareservice.interfaces.proxy;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.models.RocketData;

public interface ITelemetryProxy {

    void sendRocketData(RocketData rocket) throws TelemetryServiceUnavailableException;
}
