package fr.teama.payloadhardwaremockservice.connectors;

import fr.teama.payloadhardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadhardwaremockservice.helpers.LoggerHelper;
import fr.teama.payloadhardwaremockservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.payloadhardwaremockservice.models.PayloadData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelemetryProxy implements ITelemetryProxy {
    @Value("${rocket.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendPayloadData(PayloadData payloadData) throws TelemetryServiceUnavailableException {
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/send-payload-data", payloadData, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }
}
