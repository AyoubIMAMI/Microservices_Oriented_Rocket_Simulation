package fr.teama.missionservice.services;

import fr.teama.missionservice.connectors.externalDTO.MissionLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("rocket-topic", message);
    }

    public void warnWebcaster(String message) {
        kafkaTemplate.send("webcaster-topic", message);
    }
}