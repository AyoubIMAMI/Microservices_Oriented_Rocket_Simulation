package fr.teama.stagehardwaremockservice;

import fr.teama.stagehardwaremockservice.models.StageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static KafkaTemplate<String, StageData> kafkaStageDataTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, StageData> kafkaStageDataTemplate) {
        KafkaProducerService.kafkaStageDataTemplate = kafkaStageDataTemplate;
    }

    public void sendStageData(StageData stage) {
        kafkaStageDataTemplate.send("stage-hardware-topic", stage);
    }
}
