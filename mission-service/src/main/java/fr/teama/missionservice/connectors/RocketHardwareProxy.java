package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RocketHardwareProxy implements IRocketHardwareProxy {
    @Value("${rocket-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void startLogging() throws RocketHardwareServiceUnavailableException {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/start-logging", null, String.class);
        } catch (Exception e) {
            throw new RocketHardwareServiceUnavailableException();
        }
    }
}
