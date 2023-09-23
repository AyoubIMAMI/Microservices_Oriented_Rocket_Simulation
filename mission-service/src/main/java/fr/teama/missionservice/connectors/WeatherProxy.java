package fr.teama.missionservice.connectors;

import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.IWeatherProxy;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherProxy implements IWeatherProxy {
    @Value("${weather.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getWeatherStatus() throws WeatherServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Ask weather-service for the weather");
            ResponseEntity<String> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/weather", String.class);
            LoggerHelper.logInfo("The weather service replied \"" + response.getBody() + "\"");
            return response.getBody();
        } catch (Exception e) {
            LoggerHelper.logError("Weather service is unavailable");
            throw new WeatherServiceUnavailableException();
        }
    }
}
