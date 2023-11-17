package fr.teama.robotdepartmentservice.services;

import fr.teama.robotdepartmentservice.connectors.externalDTO.*;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
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

    public void askRocketHeight() {
        TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingFieldDTO.HEIGHT, 12000.0, OperationTypeDTO.GREATER_OR_EQUAL);
        List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
        trackItemDTOList.add(trackItemDTO);

        TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "robot-department", TrackingCategoryDTO.ROCKET, "drop");
        LoggerHelper.logInfo("Ask telemetry to being notify when the rocket reach the height of " + trackItemDTO.getData());
        kafkaTrackingTemplate.send("tracking-topic", trackingDTO);
    }

    public void askRobotHeight() {
        TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingFieldDTO.HEIGHT, 0.0, OperationTypeDTO.LESS_OR_EQUAL);
        List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
        trackItemDTOList.add(trackItemDTO);

        TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "robot-department", TrackingCategoryDTO.ROBOT, "landed");
        LoggerHelper.logInfo("Ask telemetry to being notify when the robot reach the height of " + trackItemDTO.getData());
        kafkaTrackingTemplate.send("tracking-topic", trackingDTO);
    }

    public void askRobotPosition(double x, double y) {
        TrackItemDTO trackItemDTOX = new TrackItemDTO(TrackingFieldDTO.X, x, OperationTypeDTO.EQUAL);
        TrackItemDTO trackItemDTOY = new TrackItemDTO(TrackingFieldDTO.Y, y, OperationTypeDTO.EQUAL);

        List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
        trackItemDTOList.add(trackItemDTOX);
        trackItemDTOList.add(trackItemDTOY);

        TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "robot-department", TrackingCategoryDTO.ROBOT, "reached-position");
        LoggerHelper.logInfo("Ask telemetry to being notify when the robot reach a x position of " + trackItemDTOX.getData()+ " and a y position of " + trackItemDTOY.getData());
        kafkaTrackingTemplate.send("tracking-topic", trackingDTO);
    }
}