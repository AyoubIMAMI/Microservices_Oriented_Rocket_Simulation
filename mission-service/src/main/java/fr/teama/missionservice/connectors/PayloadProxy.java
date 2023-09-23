package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.IPayloadProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PayloadProxy implements IPayloadProxy {

    @Value("${payload.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void missionStartNotify() throws PayloadServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Warn payload service that the mission has started");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/payload", null, String.class);
        } catch (Exception e) {
            throw new PayloadServiceUnavailableException();
        }
    }
}
