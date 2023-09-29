package fr.teama.stagehardwaremockservice.components;

import fr.teama.stagehardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.stagehardwaremockservice.helpers.LoggerHelper;
import fr.teama.stagehardwaremockservice.interfaces.IStageHardware;
import fr.teama.stagehardwaremockservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.stagehardwaremockservice.models.StageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;



@Component
public class StageHardware implements IStageHardware {

    @Autowired
    ITelemetryProxy telemetryProxy;

    private final long updateDelay = 1;

    private final Double EARTH_GRAVITY = 10.0;

    private final Double PARACHUTE_SPEED = 30.0;

    private Double DEPLOY_PARACHUTE_HEIGHT = 500.0;

    boolean sendLog;

    boolean parachuteDeployed = false;

    @Override
    public void startLogging(StageData stageData) {
        sendLog = true;
        stageData.setAcceleration(-EARTH_GRAVITY);

        while(sendLog) {
            if (stageData.getAltitude() <= DEPLOY_PARACHUTE_HEIGHT && !parachuteDeployed) {
                parachuteDeployed = true;
                LoggerHelper.logInfo("Parachute deployed for stage " + stageData.getStageLevel() + " at " + stageData.getAltitude() + " meters");
            }

            if (parachuteDeployed) {
                stageData.setSpeed(-PARACHUTE_SPEED);
            } else {
                stageData.setSpeed(stageData.getSpeed() + stageData.getAcceleration());
            }
            stageData.setAltitude(stageData.getAltitude() + stageData.getSpeed());

            if (stageData.getAltitude() <= 0) {
                stageData.setAltitude(0.0);
                stageData.setSpeed(0.0);
                stageData.setAcceleration(0.0);
                sendLog = false;
            }

            try {
                stageData.setTimestamp(java.time.LocalDateTime.now());
                telemetryProxy.sendStageData(stageData);
                TimeUnit.SECONDS.sleep(updateDelay);
            } catch (InterruptedException | TelemetryServiceUnavailableException e) {
                LoggerHelper.logError(e.toString());
            }
        }
    }
}
