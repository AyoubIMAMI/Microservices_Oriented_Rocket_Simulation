package fr.teama.payloadservice.services;

import fr.teama.payloadservice.connectors.externalDTO.*;
import fr.teama.payloadservice.helpers.LoggerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private final KafkaTemplate<String, TrackingDTO> kafkaTrackingTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, TrackingDTO> kafkaTrackingTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTrackingTemplate = kafkaTrackingTemplate;
    }

    public void warnWebcaster(String message) {
        kafkaTemplate.send("webcaster-topic", message);
    }

    public void missionStartNotify() {
        TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingFieldDTO.HEIGHT, 3600.0, OperationTypeDTO.GREATER_OR_EQUAL);
        List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
        trackItemDTOList.add(trackItemDTO);

        TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "payload", TrackingCategoryDTO.ROCKET);
        LoggerHelper.logInfo("Ask telemetry to being notify when the rocket reach the height of " + trackItemDTO.getData());
        kafkaTrackingTemplate.send("tracking-topic", trackingDTO);
    }
}