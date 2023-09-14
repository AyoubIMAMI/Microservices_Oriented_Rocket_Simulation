package fr.teama.weatherservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    public static final String BASE_URI = "/api/weather";

    @GetMapping(path = BASE_URI)
    public ResponseEntity<String> getWeatherStatus() {
        return ResponseEntity.ok().body("GO");
    }
}
