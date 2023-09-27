package fr.teama.telemetryservice.connectors;

import fr.teama.telemetryservice.exceptions.MissionServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.proxy.IMissionProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public class MissionProxy implements IMissionProxy {
    @Value("${mission.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void specificStatusDetected() throws MissionServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Notify mission service that the rocket has a specific status");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/mission/rocket-destruction", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new MissionServiceUnavailableException();
        }
    }
}
