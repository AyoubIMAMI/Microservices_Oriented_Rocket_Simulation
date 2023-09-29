package fr.teama.telemetryservice.connectors;

import fr.teama.telemetryservice.exceptions.ExecutiveServiceUnavailableException;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.proxy.IExecutiveProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExecutiveProxy implements IExecutiveProxy {

    @Value("${executive.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void notifyStageLanded() throws ExecutiveServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Notify the executive that the stage has landed");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/executive/stage-landed", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new ExecutiveServiceUnavailableException();
        }
    }
}
