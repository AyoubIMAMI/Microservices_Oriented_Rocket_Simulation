package fr.teama.executiveservice.services;

import fr.teama.executiveservice.helpers.LoggerHelper;
import fr.teama.executiveservice.interfaces.IDataAsker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private IDataAsker dataAsker;

    @KafkaListener(topics = "executive-event-topic", groupId = "group_id")
    void executiveEvent(String event) {
        event = event.substring(1, event.length() - 1);
        switch (event) {
            case "stage-landed":
                LoggerHelper.logInfo("The stage has successfully landed");
                break;
            default:
                LoggerHelper.logWarn("Unknown event received");
        }
    }

    @KafkaListener(topics = "start-event-topic", groupId = "group_id_executive")
    public void missionStartWarning() {
        LoggerHelper.logInfo("Notification of the start of the mission");
        dataAsker.askStageHeightToTelemetry();
    }
}