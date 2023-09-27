package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.ExecutiveServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.IExecutiveProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExecutiveProxy implements IExecutiveProxy {

    @Value("${executive.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void missionStartNotification() throws ExecutiveServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform payload service that the mission has started");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/executive", null, String.class);
        } catch (Exception e) {
            throw new ExecutiveServiceUnavailableException();
        }
    }
}
