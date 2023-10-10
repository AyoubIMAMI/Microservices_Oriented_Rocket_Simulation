package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import fr.teama.missionservice.exceptions.WebcasterServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.IWebcasterProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebcasterProxy implements IWebcasterProxy {
    @Value("${webcaster.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    public void warnWebcaster(String logs) throws WebcasterServiceUnavailableException {
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/webcaster", logs, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Webcaster service is unavailable");
            throw new WebcasterServiceUnavailableException();
        }
    }
}
