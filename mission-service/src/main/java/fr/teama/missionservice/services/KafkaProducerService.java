package fr.teama.missionservice.services;

import fr.teama.missionservice.connectors.externalDTO.TrackItemDTO;
import fr.teama.missionservice.connectors.externalDTO.TrackingDTO;
import fr.teama.missionservice.exceptions.NotifyStateNotSupportedException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.models.OperationType;
import fr.teama.missionservice.models.RocketStates;
import fr.teama.missionservice.models.TrackingCategory;
import fr.teama.missionservice.models.TrackingField;
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

    public void sendMessage(String message) {
        kafkaTemplate.send("rocket-topic", message);
    }

    public void warnWebcaster(String message) {
        kafkaTemplate.send("webcaster-topic", message);
    }

    public void gettingNotifyInCaseOfRocketAnomaly(RocketStates rocketState) throws NotifyStateNotSupportedException {
        TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingField.STATUS, rocketState.getValue(), OperationType.EQUAL);
        List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
        trackItemDTOList.add(trackItemDTO);
        TrackingDTO trackingDTO = switch (rocketState) {
            case SEVERE_ANOMALY ->
                    new TrackingDTO(trackItemDTOList, "mission", TrackingCategory.ROCKET, "/mission/rocket-hardware-destruction");
            case PRESSURE_ANOMALY ->
                    new TrackingDTO(trackItemDTOList, "mission", TrackingCategory.ROCKET, "/mission/rocket-pressure-anomaly");
            default -> throw new NotifyStateNotSupportedException();
        };
        LoggerHelper.logInfo("Ask telemetry to being notify in case of rocket anomaly detection with rocket status : " + trackItemDTO.getData());
        kafkaTrackingTemplate.send("tracking-topic", trackingDTO);
    }
}