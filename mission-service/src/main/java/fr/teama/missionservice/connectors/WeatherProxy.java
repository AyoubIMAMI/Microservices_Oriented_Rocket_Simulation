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
        ResponseEntity<String> response = restTemplate.getForEntity(apiBaseUrlHostAndPort + "/weather", String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new WeatherServiceUnavailableException();
        }
    }
}