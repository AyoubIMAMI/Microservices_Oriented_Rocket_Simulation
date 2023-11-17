package fr.teama.executiveservice.services;

import fr.teama.executiveservice.helpers.LoggerHelper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

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
}