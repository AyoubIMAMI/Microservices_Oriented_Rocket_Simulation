package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Value;
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
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/start-logging", null, String.class);
        } catch (Exception e) {
            throw new RocketHardwareServiceUnavailableException();
        }
    }
}
