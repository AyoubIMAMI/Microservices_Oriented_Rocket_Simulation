package fr.teama.missionservice.components;

import fr.teama.missionservice.exceptions.*;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.IMissionManager;
import fr.teama.missionservice.interfaces.proxy.*;
import fr.teama.missionservice.models.RocketStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;


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

    @Autowired
    IWebcasterProxy webcasterProxy;

    @Override
    public ResponseEntity<String> startMission() throws RocketServiceUnavailableException, WeatherServiceUnavailableException, RocketHardwareServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, TelemetryServiceUnavailableException, WebcasterServiceUnavailableException {
        LoggerHelper.logInfo("The mission is starting");
        rocketHardwareProxy.startLogging();

        LoggerHelper.logInfo("Rocket preparation started");
        webcasterProxy.warnWebcaster("Rocket preparation started");
        Double rocketStatus = rocketHardwareProxy.checkRocket();

        boolean weatherServiceReady = weatherProxy.getWeatherStatus().equals("GO");
        boolean rocketServiceReady = rocketDepartmentProxy.getRocketStatus().equals("GO");
        boolean missionReady = rocketStatus == RocketStates.NORMAL.getValue() && weatherServiceReady && rocketServiceReady;

        logServiceMessage(weatherServiceReady, "Weather service");
        logServiceMessage(rocketServiceReady, "Rocket department service");
        logServiceMessage(missionReady, "Mission service");

        LoggerHelper.logInfo("Rocket preparation complete");
        webcasterProxy.warnWebcaster("Rocket preparation complete");

        if (missionReady) {
            LoggerHelper.logInfo("Rocket is on Internal Power");
            webcasterProxy.warnWebcaster("Rocket is on Internal Power");
            gettingNotifyInCaseOfRocketAnomaly();
            NotifyMissionStart();
            rocketDepartmentProxy.launchRocket();
            return ResponseEntity.ok().body("GO");
        } else {
            LoggerHelper.logWarn("CANNOT LAUNCH ROCKET");
            return ResponseEntity.ok().body("NO GO");
        }
    }

    @Override
    public void missionSuccess() throws RocketHardwareServiceUnavailableException, LogsServiceUnavailableException, WebcasterServiceUnavailableException {
        LoggerHelper.logWarn("The mission has succeed !!!");
        webcasterProxy.warnWebcaster("The mission has succeed !!!");
        rocketHardwareProxy.stopLogging();
        LoggerHelper.logInfoWithoutSaving("Number of logs of the missions : " + Objects.requireNonNull(logsProxy.getAllLogs().getBody()).size());
    }

    @Override
    public void missionFailed() throws RocketHardwareServiceUnavailableException, LogsServiceUnavailableException {
        LoggerHelper.logWarn("The mission has failed due to unexpected events");
        rocketHardwareProxy.stopLogging();
        LoggerHelper.logInfoWithoutSaving("Number of logs of the missions : " + Objects.requireNonNull(logsProxy.getAllLogs().getBody()).size());
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
        telemetryProxy.gettingNotifyInCaseOfRocketAnomaly(RocketStates.SEVERE_ANOMALY);
        telemetryProxy.gettingNotifyInCaseOfRocketAnomaly(RocketStates.PRESSURE_ANOMALY);
    }
}
