package fr.teama.executiveservice.components;

import fr.teama.executiveservice.interfaces.IDataAsker;
import fr.teama.executiveservice.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataAsker implements IDataAsker {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Override
    public void askStageHeightToTelemetry() {
        kafkaProducerService.missionStartNotify();
    }
}
