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
    IRocketDepartmentProxy rocketDepartmentProxy;

    @Autowired
    IPayloadProxy payloadProxy;

    @Autowired
    IExecutiveProxy executiveProxy;

    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @Autowired
    ITelemetryProxy telemetryProxy;

    @Autowired
    ILogsProxy logsProxy;


    @Override
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("The mission is starting");

        rocketHardwareProxy.startLogging();

        boolean weatherServiceReady = weatherProxy.getWeatherStatus().equals("GO");
        boolean rocketServiceReady = rocketDepartmentProxy.getRocketStatus().equals("GO");
        boolean missionReady = weatherServiceReady && rocketServiceReady;

        logServiceMessage(weatherServiceReady, "Weather service");
        logServiceMessage(rocketServiceReady, "Rocket department service");
        logServiceMessage(missionReady, "Mission service");

        if (missionReady) {
            gettingNotifyInCaseOfRocketAnomaly();
            NotifyMissionStart();
            rocketDepartmentProxy.launchRocket();
            return ResponseEntity.ok().body("GO");
        } else {
            return ResponseEntity.ok().body("NO GO");
        }
    }

    @Override
    public void missionSuccess() throws RocketHardwareServiceUnavailableException, LogsServiceUnavailableException {
        LoggerHelper.logWarn("The mission has succeed !!!");
        rocketHardwareProxy.stopLogging();
        LoggerHelper.logInfoWithoutSaving("All logs of the missions : " + logsProxy.getAllLogs().getBody());
    }

    @Override
    public void missionFailed() throws RocketHardwareServiceUnavailableException, LogsServiceUnavailableException {
        LoggerHelper.logWarn("The mission has failed due to unexpected events");
        rocketHardwareProxy.stopLogging();
        LoggerHelper.logInfoWithoutSaving("All logs of the missions : " + logsProxy.getAllLogs().getBody());
    }

    private void logServiceMessage(boolean serviceReady, String serviceName) {
        if (serviceReady)
            LoggerHelper.logInfo(serviceName + " is OK to launch rocket");
        else
            LoggerHelper.logWarn(serviceName + " is not OK to launch rocket");
    }

    private void NotifyMissionStart() throws PayloadServiceUnavailableException, ExecutiveServiceUnavailableException {
        payloadProxy.missionStartNotification();
        executiveProxy.missionStartNotification();
    }

    private void gettingNotifyInCaseOfRocketAnomaly() throws TelemetryServiceUnavailableException {
        telemetryProxy.gettingNotifyInCaseOfRocketAnomaly();
    }
}
