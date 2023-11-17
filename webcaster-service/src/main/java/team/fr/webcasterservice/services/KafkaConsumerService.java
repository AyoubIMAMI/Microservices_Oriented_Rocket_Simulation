package team.fr.webcasterservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import team.fr.webcasterservice.interfaces.IWebcaster;

@Service
public class KafkaConsumerService {

    private final IWebcaster webcaster;

    @Autowired
    public KafkaConsumerService(IWebcaster webcaster) {
        this.webcaster = webcaster;
    }

    @KafkaListener(topics = "webcaster-topic", groupId = "group_id")
    void receiveLogs(String logs) {
        this.webcaster.sayOnAir(logs);
    }
}