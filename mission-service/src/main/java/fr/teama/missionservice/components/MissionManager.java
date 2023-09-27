package fr.teama.missionservice.components;

import fr.teama.missionservice.exceptions.*;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.IMissionManager;
import fr.teama.missionservice.interfaces.proxy.*;
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
    IPayloadProxy payloadProxy;

    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @Autowired
    ITelemetryProxy telemetryProxy;

    @Override
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException, PayloadServiceUnavailableException, TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("The mission is starting");

        rocketHardwareProxy.startLogging();

        boolean weatherServiceReady = weatherProxy.getWeatherStatus().equals("GO");
        boolean rocketServiceReady = rocketProxy.getRocketStatus().equals("GO");
        boolean missionReady = weatherServiceReady && rocketServiceReady;

        logServiceMessage(weatherServiceReady, "Weather service");
        logServiceMessage(rocketServiceReady, "Rocket department service");
        logServiceMessage(missionReady, "Mission service");

        if (missionReady) {
            gettingNotifyInCaseOfRocketAnomaly();
            missionStartWarning();
            rocketProxy.launchRocket();
            return ResponseEntity.ok().body("GO");
        } else {
            return ResponseEntity.ok().body("NO GO");
        }
    }

    private void logServiceMessage(boolean serviceReady, String serviceName) {
        if (serviceReady)
            LoggerHelper.logInfo(serviceName + " is ready");
        else
            LoggerHelper.logWarn(serviceName + " is not ready");
    }

    private void missionStartWarning() throws PayloadServiceUnavailableException {
        payloadProxy.missionStartNotify();
    }

    private void gettingNotifyInCaseOfRocketAnomaly() throws TelemetryServiceUnavailableException {
        telemetryProxy.gettingNotifyInCaseOfRocketAnomaly();
    }
}
