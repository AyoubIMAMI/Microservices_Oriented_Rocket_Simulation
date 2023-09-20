package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.interfaces.proxy.IRocketProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RocketProxy implements IRocketProxy {
    @Value("${rocket.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getRocketStatus() throws RocketServiceUnavailableException {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/rocket/status", String.class);

            return response.getBody();
        } catch (Exception e) {
            throw new RocketServiceUnavailableException();
        }
    }

    @Override
    public void postLaunchOrder() throws RocketServiceUnavailableException {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket/launch", null, String.class);
        } catch (Exception e) {
            throw new RocketServiceUnavailableException();
        }
    }
}
