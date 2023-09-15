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
        boolean weatherServiceReady = weatherProxy.getWeatherStatus().equals("GO");
        boolean rocketServiceReady = rocketProxy.getRocketStatus().equals("GO");

        printServiceMessage(weatherServiceReady, "Weather service ready", "Weather service NOT ready");
        printServiceMessage(rocketServiceReady, "Rocket service ready", "Rocket service NOT ready");

        return ResponseEntity.ok().body(weatherServiceReady && rocketServiceReady ? "GO" : "NO GO");
    }

    public void printServiceMessage(boolean isServiceReady, String readyMessage, String notReadyMessage) {
        System.out.println(isServiceReady ? readyMessage : notReadyMessage);
    }
}
