package fr.teama.payloadservice.connectors;

import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelemetryProxy implements ITelemetryProxy {

    @Value("${telemetry.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> missionStartNotify() throws TelemetryServiceUnavailableException {
        try {
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/tracking", null, String.class);
        } catch (Exception e) {
            throw new TelemetryServiceUnavailableException();
        }
    }
}
