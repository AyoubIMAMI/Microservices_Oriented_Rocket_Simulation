package fr.teama.missionservice.connectors;

import fr.teama.missionservice.interfaces.proxy.IRocketProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RocketProxy implements IRocketProxy {
    @Value("${api.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getRocketStatus() {
        ResponseEntity<String> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/rocket", String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Impossible to fetch rocket data.");
        }
    }
}
