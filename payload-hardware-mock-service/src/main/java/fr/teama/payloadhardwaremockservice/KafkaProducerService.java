package fr.teama.payloadhardwaremockservice;

import fr.teama.payloadhardwaremockservice.models.PayloadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static KafkaTemplate<String, PayloadData> kafkaPayloadDataTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, PayloadData> kafkaPayloadDataTemplate) {
        KafkaProducerService.kafkaPayloadDataTemplate = kafkaPayloadDataTemplate;
    }

    public void sendPayloadData(PayloadData payload) {
        kafkaPayloadDataTemplate.send("payload-hardware-topic", payload);
    }
    
}
