package fr.teama.weatherservice.controllers;

import fr.teama.weatherservice.interfaces.IWeatherAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    public static final String BASE_URI = "/api/weather";

    @Autowired
    private IWeatherAnalyzer weatherAnalyzer;

    @GetMapping(path = BASE_URI)
    public ResponseEntity<String> getWeatherStatus() {
        return weatherAnalyzer.getWeatherStatus();
    }
}
