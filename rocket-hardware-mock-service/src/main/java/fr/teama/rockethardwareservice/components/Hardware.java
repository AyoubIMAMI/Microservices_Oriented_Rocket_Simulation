package fr.teama.rockethardwareservice.components;

import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.IHardware;
import fr.teama.rockethardwareservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.rockethardwareservice.models.RocketData;
import fr.teama.rockethardwareservice.models.StageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;

@Component
public class Hardware implements IHardware {

    @Autowired
    ITelemetryProxy telemetryProxy;

    private final long updateDelay = 1;
    RocketData rocket;

    private final Double ACCELERATION = 1.5;

    boolean sendLog;

    @Override
    public void startLogging() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("Start logging");
        rocket = new RocketData(List.of(new StageData(1, 100), new StageData(2, 250)));

        sendLog = true;

        while (sendLog) {
            if (rocket.getStages().get(0) != null) {
                StageData lowestStageOnRocket = rocket.getStages().get(0);
                if (lowestStageOnRocket.isActivated() && lowestStageOnRocket.getFuel() > 0) {
                    lowestStageOnRocket.setFuel(max(lowestStageOnRocket.getFuel() - new Random().nextDouble() * 15, 0));
                    this.rocket.setAcceleration(rocket.getAcceleration() + new Random().nextDouble() * ACCELERATION);
                } else if (lowestStageOnRocket.getFuel() <= 0) {
                    LoggerHelper.logWarn("No more fuel in stage " + lowestStageOnRocket.getStageLevel() + ". Waiting for stage to be detached");
                    this.rocket.setAcceleration(max(this.rocket.getAcceleration() - new Random().nextDouble() * ACCELERATION, 0));
                } else if (!lowestStageOnRocket.isActivated()) {
                    LoggerHelper.logWarn("Stage " + lowestStageOnRocket.getStageLevel() + " is not activated. Waiting for stage to be activated");
                    this.rocket.setAcceleration(max(this.rocket.getAcceleration() - new Random().nextDouble() * ACCELERATION, 0));
                }

                rocket.setSpeed(rocket.getSpeed() + rocket.getAcceleration());
                rocket.setAltitude(rocket.getAltitude() + rocket.getSpeed());
            } else {
                //LoggerHelper.logWarn("No more stages on rocket");
                this.rocket.setAcceleration(max(this.rocket.getAcceleration() - new Random().nextDouble() * ACCELERATION, 0));
            }

            try {
                rocket.setTimestamp(java.time.LocalDateTime.now());
                telemetryProxy.sendRocketData(rocket);
                TimeUnit.SECONDS.sleep(updateDelay);
            } catch (InterruptedException e) {
                LoggerHelper.logError(e.toString());
            }
        }
    }

    @Override
    public void stopLogging() {
        LoggerHelper.logInfo("Stop logging");
        sendLog = false;
    }

    @Override
    public void sabotageTheRocket() {
        LoggerHelper.logWarn("Rocket successfully sabotaged");
        rocket.setStatus(0.0);
    }

    @Override
    public void destroyHardware() {
        LoggerHelper.logWarn("Rocket destroyed to prevent potential damage");
        sendLog = false;
    }

    @Override
    public void stageRocket() {
        LoggerHelper.logInfo("Stage rocket physically");
        StageData stageToDetach = rocket.getStages().get(0);
        rocket.setStages(rocket.getStages().subList(1, rocket.getStages().size()));
        stageToDetach.setActivated(false);
        stageToDetach.setDetached(true);
        //TODO: Send stage to stage mock service
    }

    @Override
    public void activateStageRocket() {
        LoggerHelper.logInfo("Activate lowest stage of the rocket, acceleration will increase");
        this.rocket.getStages().get(0).setActivated(true);
    }

    @Override
    public void deactivateStageRocket() {
        LoggerHelper.logInfo("Deactivate lowest stage of the rocket, acceleration will decrease");
        this.rocket.getStages().get(0).setActivated(false);
    }
}
