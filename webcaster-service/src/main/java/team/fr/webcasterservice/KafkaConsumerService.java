package team.fr.webcasterservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import team.fr.webcasterservice.interfaces.IWebcaster;

@Component
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