package fr.teama.rockethardwareservice.connectors;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.rockethardwareservice.models.Rocket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelemetryProxy implements ITelemetryProxy {
    @Value("${rocket.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendRocketData(Rocket rocket) throws TelemetryServiceUnavailableException {
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/send-data", rocket, String.class);
        } catch (Exception e) {
            throw new TelemetryServiceUnavailableException();
        }
    }
}
