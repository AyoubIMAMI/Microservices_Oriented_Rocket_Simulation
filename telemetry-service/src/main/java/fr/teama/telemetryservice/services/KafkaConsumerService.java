package fr.teama.telemetryservice.services;

import fr.teama.telemetryservice.controllers.dto.*;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.interfaces.DataSender;
import fr.teama.telemetryservice.interfaces.ITelemetryNotifier;
import fr.teama.telemetryservice.models.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private DataSaver dataSaver;

    @Autowired
    private DataSender dataSender;

    @Autowired
    private ITelemetryNotifier telemetryNotifier;

    @KafkaListener(topics = "rocket-hardware-topic", groupId = "group_id", containerFactory = "messageRocketDataListener")
    public void receiveRocketData(RocketDataDTO rocketDataDTO) {
        LoggerHelper.logInfo("Receive \u001B[33mrocket\u001B[32m hardware data: " + rocketDataDTO.toString());
        this.dataSaver.saveRocketData(rocketDataDTO);
    }

    @KafkaListener(topics = "stage-hardware-topic", groupId = "group_id", containerFactory = "messageStageDataListener")
    public void receiveStageData(StageDataDTO stageDataDTO) {
        LoggerHelper.logInfo("Receive \u001B[38;5;165mstage\u001B[32m " + stageDataDTO.getStageLevel() + " hardware data: " + stageDataDTO.toStringComplete());
        this.dataSaver.saveStageData(stageDataDTO);
    }

    @KafkaListener(topics = "robot-hardware-topic", groupId = "group_id", containerFactory = "messageRobotDataListener")
    public void receiveRobotData(RobotDataDTO robotDataDTO) {
        LoggerHelper.logInfo("Receive \u001B[93mrobot\u001B[32m hardware data: " + robotDataDTO.toString());
        this.dataSaver.saveRobotData(robotDataDTO);
    }

    @KafkaListener(topics = "robot-hardware-sample-topic", groupId = "group_id", containerFactory = "messageRobotDataListener")
    public void receiveRobotSampleData(RobotDataDTO robotDataDTO) {
        LoggerHelper.logInfo("Receive \u001B[93mrobot\u001B[32m hardware data sample: " + robotDataDTO.toStringSample());
        this.dataSaver.saveRobotDataSample(robotDataDTO);
        this.dataSender.sendRobotDataForScientist(robotDataDTO);
    }

    @KafkaListener(topics = "payload-hardware-topic", groupId = "group_id", containerFactory = "messagePayloadDataListener")
    public void receivePayloadData(PayloadDataDTO payloadDataDTO) {
        this.dataSender.sendPayloadData(payloadDataDTO);
    }

    @KafkaListener(topics = "tracking-topic", groupId = "group_id", containerFactory = "messageTrackingListener")
    public void createTrackingNotification(TrackingDTO trackingDTO) {
        LoggerHelper.logInfo("New tracking request received from " + trackingDTO.getServiceToBeNotified() + " service");
        Tracking tracking = new Tracking(trackingDTO);
        telemetryNotifier.trackingNotify(tracking);
    }
}
