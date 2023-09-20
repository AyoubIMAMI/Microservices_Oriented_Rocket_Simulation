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

    @Autowired
    LoggerComponent logger;

    @Override
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException {
        logger.logInfo("The mission is starting");

        boolean weatherServiceReady = weatherProxy.getWeatherStatus().equals("GO");
        boolean rocketServiceReady = rocketProxy.getRocketStatus().equals("GO");
        boolean missionReady = weatherServiceReady && rocketServiceReady;

        logServiceMessage(weatherServiceReady, "Weather service");
        logServiceMessage(rocketServiceReady, "Rocket department service");
        logServiceMessage(missionReady, "Mission service");

        if (missionReady) {
            rocketProxy.postLaunchOrder();
            return ResponseEntity.ok().body("GO");
        } else {
            return ResponseEntity.ok().body("NO GO");
        }
    }

    public void logServiceMessage(boolean serviceReady, String serviceName) {
        if (serviceReady)
            logger.logInfo(serviceName + " is ready");
        else
            logger.logWarn(serviceName + " is not ready");
    }
}
