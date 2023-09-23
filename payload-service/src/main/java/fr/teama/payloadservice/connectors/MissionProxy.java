package fr.teama.payloadservice.connectors;

import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.interfaces.ILoggerComponent;
import fr.teama.payloadservice.interfaces.proxy.IMissionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MissionProxy implements IMissionProxy {
    @Value("${mission.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    @Autowired
    ILoggerComponent logger;
    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public ResponseEntity<String> missionSuccessNotify() throws TelemetryServiceUnavailableException {
        try {
            logger.logInfo("Notify mission-service that the mission that the Payload has been dropped.");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/mission/success", null, String.class);
        } catch (Exception e) {
            System.out.println(e);
            throw new TelemetryServiceUnavailableException();
        }
    }

}
