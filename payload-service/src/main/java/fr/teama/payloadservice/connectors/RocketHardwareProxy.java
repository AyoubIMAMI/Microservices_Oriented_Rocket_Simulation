package fr.teama.payloadservice.connectors;

import fr.teama.payloadservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.proxy.IRocketHardwareProxy;
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
    public ResponseEntity<String> dropPayload() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform the rocket hardware service to drop the payload");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/drop-payload", null, String.class);
            return response;
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }
}
