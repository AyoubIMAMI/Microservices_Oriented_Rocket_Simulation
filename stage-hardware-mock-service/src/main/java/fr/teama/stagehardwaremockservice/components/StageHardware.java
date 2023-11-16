package fr.teama.stagehardwaremockservice.components;

import fr.teama.stagehardwaremockservice.KafkaProducerService;
import fr.teama.stagehardwaremockservice.helpers.LoggerHelper;
import fr.teama.stagehardwaremockservice.interfaces.IStageHardware;
import fr.teama.stagehardwaremockservice.models.StageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;
import static java.lang.Math.min;


@Component
public class StageHardware implements IStageHardware {

    @Autowired
    KafkaProducerService kafkaProducerService;

    private final long updateDelay = 1;

    private final Double EARTH_GRAVITY = 10.0;

    private final Double ENTRY_BURN_ACCELERATION = 20.0;

    private Double ENTRY_BURN_HEIGHT = 900.0;

    private Double LANDING_BURN_HEIGHT = 300.0;

    private Double LANDING_BURN_ACCELERATION = 30.0;

    boolean sendLog;


    @Override
    public void startLogging(StageData stageData) {
        sendLog = true;
        stageData.setAcceleration(-EARTH_GRAVITY);
        LoggerHelper.logInfo("Stage " + stageData.getStageLevel() + " has started its flight back at altitude " + stageData.getAltitude() + " decameters");

        while(sendLog) {
            // Flip maneuver
            if (stageData.getAngle() != 0) {
                if (stageData.getAngle() > 0) {
                    stageData.setAngle(max(stageData.getAngle() - new Random().nextDouble() * 3, 0));
                } else {
                    stageData.setAngle(min(stageData.getAngle() + new Random().nextDouble() * 3, 0));
                }

                if (stageData.getAngle() == 0) {
                    LoggerHelper.logInfo("Stage " + stageData.getStageLevel() + " has finished its flip maneuver");
                }
            }

            // Guidance
            if (stageData.getX() != 0 || stageData.getY() != 0) {
                if(stageData.getX() > 0) {
                    stageData.setX(max(stageData.getX() - new Random().nextDouble() * 2, 0));
                } else {
                    stageData.setX(min(stageData.getX() + new Random().nextDouble() * 2, 0));
                }

                if(stageData.getY() > 0) {
                    stageData.setY(max(stageData.getY() - new Random().nextDouble() * 2, 0));
                } else {
                    stageData.setY(min(stageData.getY() + new Random().nextDouble() * 2, 0));
                }

                if (stageData.getX() == 0 && stageData.getY() == 0) {
                    LoggerHelper.logInfo("Stage " + stageData.getStageLevel() + " has finished its guidance maneuver");
                }
            }

            // Deploy legs and landing burn
            if (stageData.getAltitude() <= LANDING_BURN_HEIGHT && !stageData.isLegsDeployed()) {
                stageData.setLegsDeployed(true);
                if (stageData.getFuel() > 0) {
                    stageData.setActivated(true);
                    LoggerHelper.logInfo("Stage " + stageData.getStageLevel() + " has deployed its legs and started its landing burn at altitude " + stageData.getAltitude() + " decameters. More power in the engine");
                } else {
                    LoggerHelper.logWarn("Stage " + stageData.getStageLevel() + " has deployed its legs but has not enough fuel to start its landing burn at altitude " + stageData.getAltitude() + " decameters");
                }
            }

            // Entry burn
            if (stageData.getAltitude() <= ENTRY_BURN_HEIGHT && !stageData.isActivated()) {
                if (stageData.getFuel() > 0) {
                    stageData.setActivated(true);
                    LoggerHelper.logInfo("Stage " + stageData.getStageLevel() + " has started its entry burn at altitude " + stageData.getAltitude() + " decameters. Starting engine to slow down");
                } else {
                    LoggerHelper.logWarn("Stage " + stageData.getStageLevel() + " has not enough fuel to start its entry burn at altitude " + stageData.getAltitude() + " decameters");
                }
            }

            // Acceleration and speed
            if (stageData.isActivated()) {
                if (stageData.isLegsDeployed() && stageData.getFuel() >= 2) {
                    stageData.setAcceleration(LANDING_BURN_ACCELERATION);
                    stageData.setSpeed(min(stageData.getSpeed() + stageData.getAcceleration(), -10));
                    stageData.setFuel(stageData.getFuel() - 2);
                } else if (stageData.getFuel() >= 1) {
                    stageData.setAcceleration(ENTRY_BURN_ACCELERATION);
                    stageData.setSpeed(min(stageData.getSpeed() + stageData.getAcceleration(), -10));
                    stageData.setFuel(stageData.getFuel() - 1);
                }

                if (stageData.getFuel() <= 0) {
                    stageData.setFuel(0.0);
                    stageData.setActivated(false);
                    LoggerHelper.logInfo("No more fuel for stage " + stageData.getStageLevel());
                }
            } else {
                stageData.setAcceleration(-EARTH_GRAVITY);
                stageData.setSpeed(stageData.getSpeed() + stageData.getAcceleration());
            }

            // Altitude
            stageData.setAltitude(stageData.getAltitude() + stageData.getSpeed());

            if (stageData.getAltitude() <= 0) {
                stageData.setAltitude(0.0);
                stageData.setSpeed(0.0);
                stageData.setAcceleration(0.0);
                stageData.setActivated(false);

                LoggerHelper.logInfo("Stage " + stageData.getStageLevel() + " has landed. End of logging");
                sendLog = false;
            }

            // Send datas
            try {
                stageData.setTimestamp(java.time.LocalDateTime.now());
                kafkaProducerService.sendStageData(stageData);
                TimeUnit.SECONDS.sleep(updateDelay);
            } catch (InterruptedException e) {
                LoggerHelper.logError(e.toString());
            }
        }
    }
}
