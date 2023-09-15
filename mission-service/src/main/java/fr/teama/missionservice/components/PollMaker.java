package fr.teama.missionservice.components;

import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import fr.teama.missionservice.interfaces.IPollMaker;
import fr.teama.missionservice.interfaces.proxy.IRocketProxy;
import fr.teama.missionservice.interfaces.proxy.IWeatherProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PollMaker implements IPollMaker {

    @Autowired
    IWeatherProxy weatherProxy;

    @Autowired
    IRocketProxy rocketProxy;

    @Override
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException {
        String weatherStatus = weatherProxy.getWeatherStatus();
        String rocketStatus = rocketProxy.getRocketStatus();

        if (weatherStatus.equals("GO") && rocketStatus.equals("GO")) {
            rocketProxy.postLaunchOrder();
            return ResponseEntity.ok().body("GO");
        } else {
            return ResponseEntity.ok().body("NO GO");
        }
    }
}
