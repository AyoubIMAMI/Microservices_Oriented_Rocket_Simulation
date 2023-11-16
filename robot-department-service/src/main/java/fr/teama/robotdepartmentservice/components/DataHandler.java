package fr.teama.robotdepartmentservice.components;

import fr.teama.robotdepartmentservice.interfaces.IDataAsker;
import fr.teama.robotdepartmentservice.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataHandler implements IDataAsker {

    @Autowired
    private KafkaProducerService kafkaProducerService;


    @Override
    public void askDataToTelemetry() {
        kafkaProducerService.askRocketHeight();
        kafkaProducerService.askRobotHeight();
    }
}
