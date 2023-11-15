package fr.teama.logsservice.services;

import fr.teama.logsservice.components.LogsManager;
import fr.teama.logsservice.controllers.dto.MissionLogDTO;
import fr.teama.logsservice.helpers.LoggerHelper;
import fr.teama.logsservice.models.MissionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerService {

    @Autowired
    LogsManager logsManager;

    @KafkaListener(topics = "logs-topic", groupId = "group_id", containerFactory = "messageListener")
    public void receiveMessage(MissionLogDTO missionLog) {
        LoggerHelper.logInfo("Received kafka message: " + missionLog);
        logsManager.saveLog(missionLog.getServiceName(), missionLog.getText(), missionLog.getDate());
    }
}