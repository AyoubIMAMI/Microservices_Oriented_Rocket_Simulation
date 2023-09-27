package fr.teama.payloadhadwaremockservice.connectors;

import fr.teama.payloadhadwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadhadwaremockservice.helpers.LoggerHelper;
import fr.teama.payloadhadwaremockservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.payloadhadwaremockservice.models.PayloadData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelemetryProxy implements ITelemetryProxy {
    @Value("${rocket.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendPayloadData(PayloadData rocket) throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("Send rocket data to telemetry service : " + rocket.toString());
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/send-data", rocket, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }
}
