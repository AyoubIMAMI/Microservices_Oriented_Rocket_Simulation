package fr.teama.missionservice.connectors;

import fr.teama.missionservice.connectors.externalDTO.TrackItemDTO;
import fr.teama.missionservice.connectors.externalDTO.TrackingDTO;
import fr.teama.missionservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.missionservice.models.OperationType;
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
    public void gettingNotifyInCaseOfRocketAnomaly() throws TelemetryServiceUnavailableException {
        try {
            TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingField.STATUS, 0.0, OperationType.EQUAL);
            List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
            trackItemDTOList.add(trackItemDTO);
            TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "mission", TrackingCategory.ROCKET);
            LoggerHelper.logInfo("Ask telemetry to being notify in case of rocket anomaly detection with rocket status : " + trackItemDTO.getData());
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }
}
