package fr.teama.rocketdepartmentservice.connectors;

import fr.teama.rocketdepartmentservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RocketHardwareProxy implements IRocketHardwareProxy {
    @Value("${rocket-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void slowDown() {
        restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/slow-down", null, String.class);
    }

    @Override
    public void speedUp() {
        restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/speed-up", null, String.class);
    }
}
