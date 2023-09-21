package fr.teama.telemetryservice.connectors;

import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.proxy.IPayloadProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayloadProxy implements IPayloadProxy {
    @Value("${payload.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public void heightReached() throws PayloadServiceUnavailableException {
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/payload/drop", null, String.class);
        } catch (Exception e) {
            throw new PayloadServiceUnavailableException();
        }
    }
}
