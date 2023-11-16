package fr.teama.robothardwaremockservice;

import fr.teama.robothardwaremockservice.models.RobotData;
import fr.teama.robothardwaremockservice.models.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static KafkaTemplate<String, RobotData> kafkaRobotDataTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, RobotData> kafkaRobotDataTemplate) {
        KafkaProducerService.kafkaRobotDataTemplate = kafkaRobotDataTemplate;
    }

    public void sendRobotData(RobotData robot) {
        kafkaRobotDataTemplate.send("robot-hardware-topic", robot);
    }

    public void sendSampleData(RobotData robot) {
        Sample sample = new Sample();
        sample.addMineral("Pyroxene");
        sample.addMineral("Olivine");
        sample.addMineral("Ilmenite");
        robot.setSample(sample);
        kafkaRobotDataTemplate.send("robot-hardware-sample-topic", robot);
    }
}
