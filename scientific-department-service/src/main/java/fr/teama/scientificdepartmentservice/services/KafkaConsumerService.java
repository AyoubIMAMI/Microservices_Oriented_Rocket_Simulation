package fr.teama.scientificdepartmentservice.services;

import fr.teama.scientificdepartmentservice.helpers.LoggerHelper;
import fr.teama.scientificdepartmentservice.interfaces.ISampleAnalyzer;
import fr.teama.scientificdepartmentservice.models.RobotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    ISampleAnalyzer sampleAnalyzer;

    @KafkaListener(topics = "sample-event-topic", groupId = "group_id", containerFactory = "messageRobotDataListener")
    public void receiveRobotSampleData(RobotData robotData) {
        LoggerHelper.logInfo("Sample received for analyze");
        sampleAnalyzer.analyzeSample(robotData.getSample());
    }
}
