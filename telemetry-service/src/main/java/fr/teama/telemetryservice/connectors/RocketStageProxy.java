package fr.teama.telemetryservice.connectors;

import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.proxy.IRocketStageProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RocketStageProxy implements IRocketStageProxy {
    @Value("${rocket-stage.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void fuelLevelReached() {
        restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket/start-logging", null, String.class);
    }
}
