package fr.teama.robothardwaremockservice.components;

import fr.teama.robothardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.robothardwaremockservice.helpers.LoggerHelper;
import fr.teama.robothardwaremockservice.interfaces.IRobotHardware;
import fr.teama.robothardwaremockservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.robothardwaremockservice.models.Position;
import fr.teama.robothardwaremockservice.models.RobotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.max;
import static java.lang.Math.min;


@Component
public class RobotHardware implements IRobotHardware {

    @Autowired
    ITelemetryProxy telemetryProxy;

    private final long updateDelay = 1;

    private final Double MOON_GRAVITY = 5.0;

    private final Double PARACHUTE_ACCELERATION = 20.0;

    private final Double DEPLOY_PARACHUTE_HEIGHT = 300.0;

    boolean sendLogForMovement;
    boolean sendLog;


    RobotData robotData;

    @Override
    public void startLoggingLanding(Position position) {
        robotData = new RobotData(position);
        sendLog = true;
        robotData.setAcceleration(-MOON_GRAVITY);

        LoggerHelper.logInfo("Robot has started its its descent to the Moon");

        while (sendLog) {

            // Flip maneuver
            if (robotData.getAngle() != 0) {
                if (robotData.getAngle() > 0) {
                    robotData.setAngle(max(robotData.getAngle() - new Random().nextDouble() * 5, 0));
                } else {
                    robotData.setAngle(min(robotData.getAngle() + new Random().nextDouble() * 5, 0));
                }

                if (robotData.getAngle() == 0) {
                    LoggerHelper.logInfo("Robot has finished its flip maneuver");
                }
            }

            // Guidance
            if (robotData.getX() != 0 || robotData.getY() != 0) {
                if (robotData.getX() > 0) {
                    robotData.setX(max(robotData.getX() - new Random().nextDouble() * 2, 0));
                } else {
                    robotData.setX(min(robotData.getX() + new Random().nextDouble() * 2, 0));
                }

                if (robotData.getY() > 0) {
                    robotData.setY(max(robotData.getY() - new Random().nextDouble() * 2, 0));
                } else {
                    robotData.setY(min(robotData.getY() + new Random().nextDouble() * 2, 0));
                }

                if (robotData.getX() == 0 && robotData.getY() == 0) {
                    LoggerHelper.logInfo("Robot has finished its guidance maneuver");
                }
            }

            // Deploy parachute
            if (robotData.getAltitude() <= DEPLOY_PARACHUTE_HEIGHT && !robotData.isParachuteDeployed()) {
                LoggerHelper.logInfo("Robot has deployed its parachute at " + robotData.getAltitude() + " decameters");
                robotData.setParachuteDeployed(true);
                robotData.setAcceleration(PARACHUTE_ACCELERATION);
            }

            // Speed
            if (robotData.isParachuteDeployed()) {
                robotData.setSpeed(min(robotData.getSpeed() + robotData.getAcceleration(), -MOON_GRAVITY));
            } else {
                robotData.setSpeed(robotData.getSpeed() + robotData.getAcceleration());
            }

            // Altitude
            robotData.setAltitude(robotData.getAltitude() + robotData.getSpeed());

            if (robotData.getAltitude() <= 0) {
                robotData.setAltitude(0.0);
                robotData.setSpeed(0.0);
                robotData.setAcceleration(0.0);

                LoggerHelper.logWarn("Robot has landed on the Moon");
            }

            // Send datas
            try {
                robotData.setTimestamp(java.time.LocalDateTime.now());
                telemetryProxy.sendRobotData(robotData);
                TimeUnit.SECONDS.sleep(updateDelay);
                if (robotData.getAltitude() == 0) {
                    sendLog = false;
                }
            } catch (InterruptedException | TelemetryServiceUnavailableException e) {
                LoggerHelper.logError(e.toString());
            }
        }
    }

    @Override
    public void startLoggingMovement(Position positionToReach){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while(sendLog){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        LoggerHelper.logInfo("Robot has started to move to the position : "+ positionToReach.toString());
        robotData.setParachuteDeployed(false);
        robotData.setSpeed(10.0);
        sendLogForMovement = true;
        while (sendLogForMovement) {
            // Guidance
            if (Math.abs(robotData.getX() - positionToReach.getX()) <= 10) {
                robotData.setX(positionToReach.getX());
            }
            else if (robotData.getX() > positionToReach.getX()) {
                robotData.setX(robotData.getX() - new Random().nextDouble() * 10);
            } else {
                robotData.setX(robotData.getX() + new Random().nextDouble() * 10);
            }

            //if the robot is close to target set y to target
            if (Math.abs(robotData.getY() - positionToReach.getY()) <= 10) {
                robotData.setY(positionToReach.getY());
            }
            else if (robotData.getY() > positionToReach.getY()) {
                robotData.setY(robotData.getY() - new Random().nextDouble() * 10);
            } else {
                robotData.setY(robotData.getY() + new Random().nextDouble() * 10);
            }
            try {
                if (Objects.equals(robotData.getX(), positionToReach.getX()) && Objects.equals(robotData.getY(), positionToReach.getY())) {
                    LoggerHelper.logWarn("Robot has finished its autopilot guidance maneuver");
                }
                robotData.setTimestamp(java.time.LocalDateTime.now());
                telemetryProxy.sendRobotData(robotData);
                TimeUnit.SECONDS.sleep(updateDelay);
                if (Objects.equals(robotData.getX(), positionToReach.getX()) && Objects.equals(robotData.getY(), positionToReach.getY())) {
                    return ;
                }
            } catch (InterruptedException | TelemetryServiceUnavailableException e) {
                LoggerHelper.logError(e.toString());
            }
        }


    }

    @Override
    public void takeSamples() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LoggerHelper.logInfo("Robot has started to take samples");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        telemetryProxy.sendSampleData(robotData);
        LoggerHelper.logInfo("Robot has finished to take samples");
    }

    @Override
    public void stopLogging() {
        LoggerHelper.logInfo("Stop logging");
        sendLog = false;
    }
}
