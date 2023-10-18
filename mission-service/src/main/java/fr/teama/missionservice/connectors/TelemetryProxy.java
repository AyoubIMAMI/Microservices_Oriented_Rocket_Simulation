package fr.teama.missionservice.connectors;

import fr.teama.missionservice.connectors.externalDTO.TrackItemDTO;
import fr.teama.missionservice.connectors.externalDTO.TrackingDTO;
import fr.teama.missionservice.exceptions.NotifyStateNotSupportedException;
import fr.teama.missionservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.missionservice.models.OperationType;
import fr.teama.missionservice.models.RocketStates;
import fr.teama.missionservice.models.TrackingCategory;
import fr.teama.missionservice.models.TrackingField;
import org.springframework.beans.factory.annotation.Value;
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
    public void gettingNotifyInCaseOfRocketAnomaly(RocketStates rocketState) throws TelemetryServiceUnavailableException {
        try {
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
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }

    @Override
    public void resetTrackings() throws TelemetryServiceUnavailableException {
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/reset-tracking", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }

    @Override
    public void changeRocketName(String rocketName) {
        try {
            LoggerHelper.logInfo("Ask telemetry to save upcoming data with rocket name " + rocketName);
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/rocket-name", rocketName, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
        }
    }
}
