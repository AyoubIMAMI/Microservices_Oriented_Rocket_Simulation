package fr.teama.robotdepartmentservice.connectors;

import fr.teama.robotdepartmentservice.connectors.externalDTO.*;
import fr.teama.robotdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelemetryProxy implements ITelemetryProxy {

    @Value("${telemetry.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> askRocketHeight() throws TelemetryServiceUnavailableException {
        try {
            TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingFieldDTO.HEIGHT, 12000.0, OperationTypeDTO.GREATER_OR_EQUAL);
            List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
            trackItemDTOList.add(trackItemDTO);

            TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "robot-department", TrackingCategoryDTO.ROCKET, "/robot/drop");
            LoggerHelper.logInfo("Ask telemetry to being notify when the rocket reach the height of " + trackItemDTO.getData());
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }

    @Override
    public ResponseEntity<String> askRobotHeight() throws TelemetryServiceUnavailableException {
        try {
            TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingFieldDTO.HEIGHT, 0.0, OperationTypeDTO.LESS_OR_EQUAL);
            List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
            trackItemDTOList.add(trackItemDTO);

            TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "robot-department", TrackingCategoryDTO.ROBOT, "/robot/landed");
            LoggerHelper.logInfo("Ask telemetry to being notify when the robot reach the height of " + trackItemDTO.getData());
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }

    @Override
    public ResponseEntity<String> askRobotPosition(double x, double y) throws TelemetryServiceUnavailableException {
        try {
            TrackItemDTO trackItemDTOX = new TrackItemDTO(TrackingFieldDTO.X, x, OperationTypeDTO.EQUAL);
            TrackItemDTO trackItemDTOY = new TrackItemDTO(TrackingFieldDTO.Y, y, OperationTypeDTO.EQUAL);

            List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
            trackItemDTOList.add(trackItemDTOX);
            trackItemDTOList.add(trackItemDTOY);

            TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "robot-department", TrackingCategoryDTO.ROBOT, "/robot/reached-position");
            LoggerHelper.logInfo("Ask telemetry to being notify when the robot reach a x position of " + trackItemDTOX.getData()+ " and a y position of " + trackItemDTOY.getData());
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }
}
