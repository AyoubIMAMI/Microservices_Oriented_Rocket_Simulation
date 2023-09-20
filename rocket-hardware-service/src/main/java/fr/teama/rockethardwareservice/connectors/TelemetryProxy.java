package fr.teama.rockethardwareservice.connectors;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TelemetryProxy implements ITelemetryProxy {
    @Value("${rocket.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void postData(String data) throws TelemetryServiceUnavailableException {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/send-data", data, String.class);
        } catch (Exception e) {
            throw new TelemetryServiceUnavailableException();
        }
    }
}
