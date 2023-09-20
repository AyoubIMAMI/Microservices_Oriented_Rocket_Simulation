package fr.teama.missionservice.connectors;

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
            ResponseEntity<String> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/weather", String.class);

            return response.getBody();
        } catch (Exception e) {
            throw new WeatherServiceUnavailableException();
        }
    }
}
