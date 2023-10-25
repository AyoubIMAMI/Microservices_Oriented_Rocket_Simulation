package fr.teama.rockethardwareservice.components;

import fr.teama.rockethardwareservice.exceptions.MissionServiceUnvailableException;
import fr.teama.rockethardwareservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.exceptions.RobotHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import fr.teama.rockethardwareservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.IRocketHardware;
import fr.teama.rockethardwareservice.interfaces.proxy.IMissionProxy;
import fr.teama.rockethardwareservice.interfaces.proxy.IPayloadHardwareProxy;
import fr.teama.rockethardwareservice.interfaces.proxy.IRobotHardwareProxy;
import fr.teama.rockethardwareservice.interfaces.proxy.IStageHardwareProxy;
import fr.teama.rockethardwareservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.rockethardwareservice.models.RocketData;
import fr.teama.rockethardwareservice.models.RocketStates;
import fr.teama.rockethardwareservice.models.StageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Component
public class RocketHardware implements IRocketHardware {

    @Autowired
    ITelemetryProxy telemetryProxy;

    @Autowired
    IStageHardwareProxy stageHardwareProxy;

    @Autowired
    IPayloadHardwareProxy payloadHardwareProxy;

    @Autowired
    IMissionProxy missionProxy;

    @Autowired
    IRobotHardwareProxy robotHardwareProxy;

    private final long updateDelay = 500;

    RocketData rocket;

    private final Double ACCELERATION = 20.0;

    private final Double EARTH_GRAVITY = 10.0;

    boolean sendLog;

    @Override
    public void startLogging() throws TelemetryServiceUnavailableException, MissionServiceUnvailableException {
        LoggerHelper.logInfo("Start logging");
        rocket = new RocketData(List.of(new StageData(1, 0.0), new StageData(2, 0.0)));

        sendLog = true;

        while (sendLog) {
            if (rocket.getStatus() == RocketStates.CRITICAL_ANOMALY.getValue()) {
                LoggerHelper.logWarn("Rocket critical anomaly detected");
                missionProxy.warnMissionThatRocketHasCriticalAnomaly();
            }

            Double altitude = rocket.getPosition().getAltitude();
            if (rocket.getStages().get(0) != null) {
                StageData lowestStageOnRocket = rocket.getStages().get(0);
                if (lowestStageOnRocket.isActivated() && lowestStageOnRocket.getFuel() > 0) {
                    lowestStageOnRocket.setFuel(max(lowestStageOnRocket.getFuel() - 5, 0));
                    this.rocket.setAcceleration(ACCELERATION);

                    rocket.getPosition().setX(rocket.getPosition().getX() + (Math.random() * 20) - 10);
                    rocket.getPosition().setY(rocket.getPosition().getY() + (Math.random() * 20) - 10);

                } else if (lowestStageOnRocket.getFuel() <= 0 && altitude > 0) {
                    LoggerHelper.logWarn("No more fuel in stage " + lowestStageOnRocket.getStageLevel() + ". Waiting for stage to be detached");
                    this.rocket.setAcceleration(-EARTH_GRAVITY);
                } else if (!lowestStageOnRocket.isActivated() && altitude > 0) {
                    LoggerHelper.logWarn("Stage " + lowestStageOnRocket.getStageLevel() + " is not activated. Waiting for stage to be activated");
                    this.rocket.setAcceleration(-EARTH_GRAVITY);
                }

                rocket.setSpeed(min(rocket.getSpeed() + rocket.getAcceleration(), 300));
                rocket.getPosition().setAltitude(altitude + rocket.getSpeed());
            } else {
                //LoggerHelper.logWarn("No more stages on rocket");
                this.rocket.setAcceleration(-EARTH_GRAVITY);
            }

            try {
                rocket.setTimestamp(java.time.LocalDateTime.now());
                telemetryProxy.sendRocketData(rocket);
                TimeUnit.MILLISECONDS.sleep(updateDelay);
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
        rocket.setStatus(RocketStates.SEVERE_ANOMALY.getValue());
    }

    @Override
    public void destroyHardware() {
        LoggerHelper.logWarn("Rocket destroyed to prevent potential damage");
        sendLog = false;
    }

    @Override
    public void stageRocket() throws StageHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Main engine cut-off");
        LoggerHelper.logInfo("Stage rocket physically");
        StageData stageToDetach = rocket.getStages().get(0);
        rocket.setStages(rocket.getStages().subList(1, rocket.getStages().size()));
        stageToDetach.setActivated(false);
        stageHardwareProxy.startLogging(stageToDetach, rocket.getPosition(), rocket.getSpeed());
    }

    @Override
    public void activateStageRocket() {
        LoggerHelper.logInfo("Activate lowest stage of the rocket (stage " + this.rocket.getStages().get(0).getStageLevel() + "), speed will increase");
        this.rocket.getStages().get(0).setActivated(true);
    }

    @Override
    public void deactivateStageRocket() {
        LoggerHelper.logInfo("Deactivate lowest stage of the rocket, speed will decrease");
        this.rocket.getStages().get(0).setActivated(false);
    }

    public RocketData getRocketData() {
        return this.rocket;
    }

    @Override
    public void fuelingTheRocket() {
        double fuelLevel = 105.0;
        for (StageData stage : rocket.getStages()) {
            stage.setFuel(fuelLevel);
            fuelLevel += 150.0;
        }
    }

    @Override
    public void dropPayload() throws PayloadHardwareServiceUnavaibleException {
        LoggerHelper.logInfo("Drop payload physically");
        payloadHardwareProxy.startOrbitalPosDispatch(rocket.getPosition());
    }

    @Override
    public void dropRobot() throws RobotHardwareServiceUnavaibleException {
        LoggerHelper.logInfo("Drop robot physically");
        robotHardwareProxy.startRobotLogging(rocket.getPosition());
    }

    @Override
    public void fairing() {
        LoggerHelper.logInfo("Eject fairing physically");
    }

    @Override
    public void pressureAnomalyOnTheRocket() {
        LoggerHelper.logWarn("A pressure anomaly appeared on the rocket");
        rocket.setStatus(RocketStates.PRESSURE_ANOMALY.getValue());
    }

    @Override
    public void criticalSabotagingOfTheRocket() {
        LoggerHelper.logWarn("Rocket is redirected towards the earth");
        rocket.setStatus(RocketStates.CRITICAL_ANOMALY.getValue());
    }
}
