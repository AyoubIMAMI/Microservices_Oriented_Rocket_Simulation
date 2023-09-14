package fr.teama.weatherservice.components;

import fr.teama.weatherservice.interfaces.IWeatherAnalyzer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class WeatherAnalyzer implements IWeatherAnalyzer {

    @Override
    public ResponseEntity<String> getWeatherStatus() {
        return ResponseEntity.ok().body("GO");
    }
}
