package fr.teama.missionservice.components;

import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import fr.teama.missionservice.interfaces.IPollMaker;
import fr.teama.missionservice.interfaces.proxy.IRocketHardwareProxy;
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

    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @Override
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException {
        System.out.println("Start Go/No Go poll");

        System.out.println("Start rocket logging system");
        rocketHardwareProxy.startLogging();

        boolean weatherServiceReady = weatherProxy.getWeatherStatus().equals("GO");
        boolean rocketServiceReady = rocketProxy.getRocketStatus().equals("GO");
        boolean missionReady = weatherServiceReady && rocketServiceReady;

        printServiceMessage(weatherServiceReady, "Weather: GO", "Weather: NO GO");
        printServiceMessage(rocketServiceReady, "Rocket: GO", "Rocket: NO GO");
        printServiceMessage(missionReady, "Mission: GO", "Mission: NO GO");

        if (missionReady) {
            rocketProxy.postLaunchOrder();
            return ResponseEntity.ok().body("GO");
        } else {
            return ResponseEntity.ok().body("NO GO");
        }
    }

    public void printServiceMessage(boolean serviceReady, String readyMessage, String notReadyMessage) {
        System.out.println(serviceReady ? readyMessage : notReadyMessage);
    }
}
