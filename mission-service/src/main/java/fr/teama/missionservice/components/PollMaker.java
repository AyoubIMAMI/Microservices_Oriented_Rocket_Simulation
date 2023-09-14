package fr.teama.missionservice.components;

import fr.teama.missionservice.interfaces.IPollMaker;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class PollMaker implements IPollMaker {

    @Override
    public ResponseEntity<String> startMission() {
        String weatherStatus = new WeatherProxy().getWeatherStatus();
        String rocketStatus = new RocketProxy().getRocketStatus();

        if (weatherStatus.equals("GO") && rocketStatus.equals("GO"))
            return ResponseEntity.ok().body("GO");
        else
            return ResponseEntity.ok().body("NO GO");
    }
}
