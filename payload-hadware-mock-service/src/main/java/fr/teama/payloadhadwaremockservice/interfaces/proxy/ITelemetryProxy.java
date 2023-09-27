package fr.teama.payloadhadwaremockservice.interfaces.proxy;

import fr.teama.payloadhadwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadhadwaremockservice.models.PayloadData;

public interface ITelemetryProxy {

    void sendPayloadData(PayloadData payloadData) throws TelemetryServiceUnavailableException;
}
