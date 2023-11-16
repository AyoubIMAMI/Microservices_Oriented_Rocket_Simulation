package fr.teama.telemetryservice;

import fr.teama.telemetryservice.controllers.dto.PayloadDataDTO;
import fr.teama.telemetryservice.controllers.dto.RobotDataDTO;
import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import fr.teama.telemetryservice.exceptions.*;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.DataSaver;
import fr.teama.telemetryservice.interfaces.DataSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @Autowired
    private DataSaver dataSaver;

    @Autowired
    private DataSender dataSender;

    @KafkaListener(topics = "rocket-hardware-topic", groupId = "group_id", containerFactory = "messageRocketDataListener")
    public void receiveRocketData(RocketDataDTO rocketDataDTO) throws RocketStageServiceUnavailableException, ExecutiveServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Receive \u001B[33mrocket\u001B[32m hardware data: " + rocketDataDTO.toString());
        this.dataSaver.saveRocketData(rocketDataDTO);
    }

    @KafkaListener(topics = "stage-hardware-topic", groupId = "group_id", containerFactory = "messageStageDataListener")
    public void receiveStageData(StageDataDTO stageDataDTO) throws RocketStageServiceUnavailableException, ExecutiveServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Receive \u001B[38;5;165mstage\u001B[32m " + stageDataDTO.getStageLevel() + " hardware data: " + stageDataDTO.toStringComplete());
        this.dataSaver.saveStageData(stageDataDTO);
    }

    @KafkaListener(topics = "robot-hardware-topic", groupId = "group_id", containerFactory = "messageRobotDataListener")
    public void receiveRobotData(RobotDataDTO robotDataDTO) throws RocketStageServiceUnavailableException, ExecutiveServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Receive \u001B[93mrobot\u001B[32m hardware data: " + robotDataDTO.toString());
        this.dataSaver.saveRobotData(robotDataDTO);
    }

    @KafkaListener(topics = "robot-hardware-sample-topic", groupId = "group_id", containerFactory = "messageRobotDataListener")
    public void receiveRobotSampleData(RobotDataDTO robotDataDTO) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException, ExecutiveServiceUnavailableException, MissionServiceUnavailableException, RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Receive \u001B[93mrobot\u001B[32m hardware data: " + robotDataDTO.toString());
        this.dataSaver.saveRobotData(robotDataDTO);
        this.dataSender.sendRobotDataForScientist(robotDataDTO);
    }

    @KafkaListener(topics = "payload-hardware-topic", groupId = "group_id", containerFactory = "messagePayloadDataListener")
    public void receivePayloadData(PayloadDataDTO payloadDataDTO) throws PayloadServiceUnavailableException {
        this.dataSender.sendPayloadData(payloadDataDTO);
    }
}
