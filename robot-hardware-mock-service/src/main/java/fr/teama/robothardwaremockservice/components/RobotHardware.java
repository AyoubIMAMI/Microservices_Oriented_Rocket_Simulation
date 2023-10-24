package fr.teama.robothardwaremockservice.components;

import fr.teama.robothardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.robothardwaremockservice.helpers.LoggerHelper;
import fr.teama.robothardwaremockservice.interfaces.IRobotHardware;
import fr.teama.robothardwaremockservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.robothardwaremockservice.models.Position;
import fr.teama.robothardwaremockservice.models.RobotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    boolean sendLog;

    RobotData robotData;

    @Override
    public void startLogging(Position position) {
        robotData = new RobotData(position);
        sendLog = true;
        robotData.setAcceleration(-MOON_GRAVITY);

        LoggerHelper.logInfo("Robot has started its its descent to the Moon");

        while(sendLog) {

            // Flip maneuver
            if (robotData.getAngle() != 0) {
                if (robotData.getAngle() > 0) {
                    robotData.setAngle(max(robotData.getAngle() - new Random().nextDouble() * 3, 0));
                } else {
                    robotData.setAngle(min(robotData.getAngle() + new Random().nextDouble() * 3, 0));
                }

                if (robotData.getAngle() == 0) {
                    LoggerHelper.logInfo("Robot has finished its flip maneuver");
                }
            }

            // Guidance
            if (robotData.getX() != 0 || robotData.getY() != 0) {
                if(robotData.getX() > 0) {
                    robotData.setX(max(robotData.getX() - new Random().nextDouble() * 2, 0));
                } else {
                    robotData.setX(min(robotData.getX() + new Random().nextDouble() * 2, 0));
                }

                if(robotData.getY() > 0) {
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
                robotData.setParachuteDeployed(true);
                robotData.setAcceleration(PARACHUTE_ACCELERATION);
            }

            // Speed
            if (robotData.isParachuteDeployed()) {
                robotData.setSpeed(min(robotData.getSpeed() + robotData.getAcceleration(),-MOON_GRAVITY));
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
            } catch (InterruptedException | TelemetryServiceUnavailableException e) {
                LoggerHelper.logError(e.toString());
            }
        }
    }

    @Override
    public void stopLogging() {
        LoggerHelper.logInfo("Stop logging");
        sendLog = false;
    }
}
