package fr.teama.payloadhardwaremockservice.interfaces.proxy;

import fr.teama.payloadhardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadhardwaremockservice.models.PayloadData;

public interface ITelemetryProxy {

    void sendPayloadData(PayloadData payloadData) throws TelemetryServiceUnavailableException;
}
