package fr.teama.weatherservice.interfaces;

import org.springframework.http.ResponseEntity;

public interface IWeatherAnalyzer {

    ResponseEntity<String> getWeatherStatus();
}
