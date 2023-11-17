package fr.teama.missionservice.components;

import fr.teama.missionservice.exceptions.*;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.IMissionManager;
import fr.teama.missionservice.interfaces.proxy.*;
import fr.teama.missionservice.models.RocketStates;
import fr.teama.missionservice.services.KafkaProducerService;
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
    IRocketHardwareProxy rocketHardwareProxy;

    @Autowired
    ITelemetryProxy telemetryProxy;

    @Autowired
    ILogsProxy logsProxy;

    @Autowired
    KafkaProducerService kafkaProducerService;

    @Override
    public ResponseEntity<String> startMission(String rocketName) throws RocketServiceUnavailableException, RocketHardwareServiceUnavailableException,TelemetryServiceUnavailableException, LogsServiceUnavailableException, NotifyStateNotSupportedException {
        telemetryProxy.changeRocketName(rocketName);
        telemetryProxy.resetTrackings();
        logsProxy.changeRocketName(rocketName);

        LoggerHelper.logInfo("The mission is starting for rocket " + rocketName);
        rocketHardwareProxy.startLogging();

        LoggerHelper.logInfo("Rocket " + rocketName + " preparation started");
        kafkaProducerService.warnWebcaster("Rocket " + rocketName + " preparation started");
        Double rocketStatus = rocketHardwareProxy.checkRocket();

        boolean weatherServiceReady = false;
        boolean rocketServiceReady = false;

        try {
            weatherServiceReady = weatherProxy.getWeatherStatus().equals("GO");
            rocketServiceReady = rocketDepartmentProxy.getRocketStatus().equals("GO");
        } catch (WeatherServiceUnavailableException e) {
            LoggerHelper.logError("Weather service is unavailable");
        } catch (RocketServiceUnavailableException e) {
            LoggerHelper.logError("Rocket department service is unavailable");
        }

        boolean missionReady = rocketStatus == RocketStates.NORMAL.getValue() && weatherServiceReady && rocketServiceReady;

        logServiceMessage(weatherServiceReady, "Weather service");
        logServiceMessage(rocketServiceReady, "Rocket department service");
        logServiceMessage(missionReady, "Mission service");

        LoggerHelper.logInfo("Rocket " + rocketName + " preparation complete");
        kafkaProducerService.warnWebcaster("Rocket " + rocketName + " preparation complete");

        if (missionReady) {
            LoggerHelper.logInfo("Rocket " + rocketName + " is on Internal Power");
            kafkaProducerService.warnWebcaster("Rocket " + rocketName + " is on Internal Power");
            gettingNotifyInCaseOfRocketAnomaly();
            NotifyMissionStart();
            rocketDepartmentProxy.launchRocket();
            return ResponseEntity.ok().body("GO");
        } else {
            LoggerHelper.logWarn("CANNOT LAUNCH ROCKET");
            missionFailed();
            return ResponseEntity.ok().body("NO GO");
        }
    }

    @Override
    public void missionSuccess() throws RocketHardwareServiceUnavailableException, LogsServiceUnavailableException {
        LoggerHelper.logWarn("The mission has succeed !!!");
        kafkaProducerService.warnWebcaster("The mission has succeed !!!");
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

    private void NotifyMissionStart() {
        LoggerHelper.logInfo("Send a mission start notification event");
        kafkaProducerService.sendStartEventNotification();
    }

    private void gettingNotifyInCaseOfRocketAnomaly() throws NotifyStateNotSupportedException {
        kafkaProducerService.gettingNotifyInCaseOfRocketAnomaly(RocketStates.SEVERE_ANOMALY);
        kafkaProducerService.gettingNotifyInCaseOfRocketAnomaly(RocketStates.PRESSURE_ANOMALY);
    }
}
