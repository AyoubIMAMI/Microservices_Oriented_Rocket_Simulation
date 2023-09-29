package fr.teama.stagehardwaremockservice.connectors;

import fr.teama.stagehardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.stagehardwaremockservice.helpers.LoggerHelper;
import fr.teama.stagehardwaremockservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.stagehardwaremockservice.models.StageData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TelemetryProxy implements ITelemetryProxy {
    @Value("${rocket.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendStageData(StageData stageData) throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("Send stage data to telemetry service : " + stageData.toString());
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/send-stage-data", stageData, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }
}
