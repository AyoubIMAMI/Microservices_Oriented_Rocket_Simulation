package fr.teama.scientificdepartmentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private final KafkaTemplate<String, String> kafkaStringTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaStringTemplate) {
        this.kafkaStringTemplate = kafkaStringTemplate;
    }

}

