package fr.teama.robothardwaremockservice.connectors;


import fr.teama.robothardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.robothardwaremockservice.helpers.LoggerHelper;
import fr.teama.robothardwaremockservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.robothardwaremockservice.models.RobotData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelemetryProxy implements ITelemetryProxy {
    @Value("${telemetry.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendRobotData(RobotData robotData) throws TelemetryServiceUnavailableException {
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/send-robot-data", robotData, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }
}
