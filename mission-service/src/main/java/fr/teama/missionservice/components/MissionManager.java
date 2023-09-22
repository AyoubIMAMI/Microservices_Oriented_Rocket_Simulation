package fr.teama.missionservice.components;

import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import fr.teama.missionservice.interfaces.IMissionManager;
import fr.teama.missionservice.interfaces.proxy.IPayloadProxy;
import fr.teama.missionservice.interfaces.proxy.IRocketHardwareProxy;
import fr.teama.missionservice.interfaces.proxy.IRocketProxy;
import fr.teama.missionservice.interfaces.proxy.IWeatherProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class MissionManager implements IMissionManager {

    @Autowired
    IWeatherProxy weatherProxy;

    @Autowired
    IRocketProxy rocketProxy;

    @Autowired
    LoggerComponent logger;

    @Autowired
    IPayloadProxy payloadProxy;

    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @Override
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException {
        logger.logInfo("The mission is starting");

        System.out.println("Start rocket logging system");
        rocketHardwareProxy.startLogging();

        boolean weatherServiceReady = weatherProxy.getWeatherStatus().equals("GO");
        boolean rocketServiceReady = rocketProxy.getRocketStatus().equals("GO");
        boolean missionReady = weatherServiceReady && rocketServiceReady;

        logServiceMessage(weatherServiceReady, "Weather service");
        logServiceMessage(rocketServiceReady, "Rocket department service");
        logServiceMessage(missionReady, "Mission service");

        if (missionReady) {
            missionStartWarning();
            rocketProxy.launchRocket();
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

    private void missionStartWarning() throws PayloadServiceUnavailableException {
        payloadProxy.missionStartNotify();
    }
}
