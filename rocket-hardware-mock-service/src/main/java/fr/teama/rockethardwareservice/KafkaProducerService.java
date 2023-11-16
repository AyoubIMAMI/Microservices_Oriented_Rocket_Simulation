package fr.teama.rockethardwareservice;

import fr.teama.rockethardwareservice.models.RocketData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static KafkaTemplate<String, RocketData> kafkaRocketDataTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, RocketData> kafkaRocketDataTemplate) {
        KafkaProducerService.kafkaRocketDataTemplate = kafkaRocketDataTemplate;
    }

    public void sendRocketData(RocketData rocket) {
        kafkaRocketDataTemplate.send("rocket-hardware-topic", rocket);
    }
}
