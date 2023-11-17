package fr.teama.rocketdepartmentservice.services;

import fr.teama.rocketdepartmentservice.connectors.externalDTO.*;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
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

    public void askWhenEventHappens(TrackingFieldDTO fieldToTrack, Double data, String service, String eventDataType, OperationTypeDTO operationTypeDTO, String log) {
        TrackItemDTO trackItemDTO = new TrackItemDTO(fieldToTrack, data, operationTypeDTO);
        List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
        trackItemDTOList.add(trackItemDTO);
        TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, service, eventDataType, TrackingCategoryDTO.ROCKET);
        LoggerHelper.logInfo("Ask to the telemetry service to be notified when " + log + trackItemDTO.getData());
        kafkaTrackingTemplate.send("tracking-topic", trackingDTO);
    }


}