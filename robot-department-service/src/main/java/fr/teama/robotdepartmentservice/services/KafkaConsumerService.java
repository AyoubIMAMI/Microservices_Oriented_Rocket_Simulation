package fr.teama.robotdepartmentservice.services;

import fr.teama.robotdepartmentservice.exceptions.MissionServiceUnavailableException;
import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.exceptions.ScientificDepartmentServiceUnavailableException;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.IDataAsker;
import fr.teama.robotdepartmentservice.interfaces.IRobotManager;
import fr.teama.robotdepartmentservice.interfaces.IRobotReleaser;
import fr.teama.robotdepartmentservice.interfaces.proxy.IScientificDepartmentProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private IRobotReleaser robotReleaser;

    @Autowired
    private IRobotManager robotManager;

    @Autowired
    private IScientificDepartmentProxy scientificDepartmentProxy;

    @Autowired
    private IDataAsker dataAsker;

    @KafkaListener(topics = "robot-event-topic", groupId = "group_id")
    void robotEvent(String event) throws MissionServiceUnavailableException, RocketHardwareServiceUnavailableException, RobotHardwareServiceUnavailableException, ScientificDepartmentServiceUnavailableException {
        event = event.substring(1, event.length() - 1);
        switch (event) {
            case "drop":
                LoggerHelper.logInfo("Notification received, requesting to drop the robot");
                kafkaProducerService.warnWebcaster("Robot drop requested");
                robotReleaser.dropRobot();
                break;
            case "landed":
                LoggerHelper.logInfo("Notification received, robot has landed");
                kafkaProducerService.warnWebcaster("Robot has landed");
                scientificDepartmentProxy.askDestination();
                break;
            case "reached-position":
                LoggerHelper.logInfo("Notification received, robot has reached the position");
                kafkaProducerService.warnWebcaster("Robot has reached the position");
                robotManager.takeSamples();
                break;
            default:
                LoggerHelper.logWarn("Unknown event received");
        }

    }

    @KafkaListener(topics = "start-event-topic", groupId = "group_id_robot")
    public void missionStartWarning() {
        LoggerHelper.logInfo("Notification of the start of the mission");
        dataAsker.askDataToTelemetry();
    }
}