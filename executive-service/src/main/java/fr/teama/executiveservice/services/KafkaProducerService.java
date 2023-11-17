package fr.teama.executiveservice.services;

import fr.teama.executiveservice.connectors.externalDTO.*;
import fr.teama.executiveservice.helpers.LoggerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaProducerService {
    private static KafkaTemplate<String, MissionLogDTO> kafkaTemplate;
    private static KafkaTemplate<String, TrackingDTO> kafkaTrackingTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, MissionLogDTO> kafkaTemplate, KafkaTemplate<String, TrackingDTO> kafkaTrackingTemplate) {
        KafkaProducerService.kafkaTemplate = kafkaTemplate;
        KafkaProducerService.kafkaTrackingTemplate = kafkaTrackingTemplate;
    }

    public static void sendMissionLog(String serviceName, String text) {
        MissionLogDTO missionLogDTO = new MissionLogDTO(serviceName, text, LocalDateTime.now());
        kafkaTemplate.send("logs-topic", missionLogDTO);
    }

    public void missionStartNotify() {
        TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingFieldDTO.HEIGHT, 0.0, OperationTypeDTO.LESS_OR_EQUAL);
        List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
        trackItemDTOList.add(trackItemDTO);

        TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "executive", TrackingCategoryDTO.INDEPENDENT_STAGE);
        LoggerHelper.logInfo("Ask telemetry to being notify when the stage 1 reach the height of " + trackItemDTO.getData());
        kafkaTrackingTemplate.send("tracking-topic", trackingDTO);
    }
}

