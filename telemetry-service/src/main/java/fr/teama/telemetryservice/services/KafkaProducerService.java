package fr.teama.telemetryservice.services;


import fr.teama.telemetryservice.connectors.externalDTO.MissionLogDTO;
import fr.teama.telemetryservice.controllers.dto.RobotDataDTO;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.models.PayloadData;
import fr.teama.telemetryservice.models.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaProducerService {
    private static KafkaTemplate<String, MissionLogDTO> kafkaTemplate;

    @Autowired
    private final KafkaTemplate<String, String> kafkaStringTemplate;

    @Autowired
    private final KafkaTemplate<String, PayloadData> kafkaPayloadDataTemplate;

    @Autowired
    private final KafkaTemplate<String, RobotDataDTO> kafkaRobotDataTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, MissionLogDTO> kafkaTemplate, KafkaTemplate<String, String> kafkaStringTemplate, KafkaTemplate<String, PayloadData> kafkaPayloadDataTemplate, KafkaTemplate<String, RobotDataDTO> kafkaRobotDataTemplate) {
        KafkaProducerService.kafkaTemplate = kafkaTemplate;
        this.kafkaStringTemplate = kafkaStringTemplate;
        this.kafkaPayloadDataTemplate = kafkaPayloadDataTemplate;
        this.kafkaRobotDataTemplate = kafkaRobotDataTemplate;
    }

    public static void sendMissionLog(String serviceName, String text) {
        MissionLogDTO missionLogDTO = new MissionLogDTO(serviceName, text, LocalDateTime.now());
        kafkaTemplate.send("logs-topic", missionLogDTO);
    }

    public void notifyStageLanded() {
        LoggerHelper.logInfo("Notify the executive that the stage has landed");
        kafkaStringTemplate.send("executive-event-topic", "stage-landed");
    }

    public void specificStatusDetected(String eventDataType) {
        LoggerHelper.logInfo("Notify mission service that the rocket has a specific status");
        kafkaStringTemplate.send("mission-event-topic", eventDataType);
    }

    public void payloadHeightReached() {
        LoggerHelper.logInfo("Notify payload service that the correct height has been reached");
        kafkaStringTemplate.send("payload-event-topic", "height-reached");
    }

    public void robotNotifyHeightReached() {
        LoggerHelper.logInfo("Notify the robot department that the height has reached a specific level");
        kafkaStringTemplate.send("robot-event-topic", "drop");
    }

    public void robotLandedSuccessfully() {
        LoggerHelper.logInfo("Notify the robot department that the robot has landed successfully");
        kafkaStringTemplate.send("robot-event-topic", "landed");
    }

    public void robotReachedPositionSuccessfully() {
        LoggerHelper.logInfo("Notify the robot department that the robot has reached the position successfully");
        kafkaStringTemplate.send("robot-event-topic", "reached-position");
    }

    public void fuelLevelReached() {
        LoggerHelper.logInfo("Notify the rocket department that the fuel has reached a specific level");
        kafkaStringTemplate.send("rocket-event-topic", "stage");
    }

    public void heightReached(Tracking tracking) {
        LoggerHelper.logInfo("Notify the rocket department that the rocket has reached a specific height");
        kafkaStringTemplate.send("rocket-event-topic", tracking.getEventDataType());
    }

    public void sendPayloadData(PayloadData payloadData) {
        LoggerHelper.logInfo("Transferring the data from \u001B[37mpayload\u001B[32m hardware to the payload service");
        kafkaPayloadDataTemplate.send("payload-data-topic", payloadData);
    }

    public void sendSample(RobotDataDTO robotDataDTO) {
        LoggerHelper.logInfo("Transferring Sample to the robot department service: " + robotDataDTO.getSample().toString());
        kafkaRobotDataTemplate.send("sample-event-topic", robotDataDTO);
    }
}

