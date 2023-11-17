package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelemetryProxy implements ITelemetryProxy {
    @Value("${telemetry.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void resetTrackings() throws TelemetryServiceUnavailableException {
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/reset-tracking", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }

    @Override
    public void changeRocketName(String rocketName) {
        try {
            LoggerHelper.logInfo("Ask telemetry to save upcoming data with rocket name " + rocketName);
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/rocket-name", rocketName, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
        }
    }
}
