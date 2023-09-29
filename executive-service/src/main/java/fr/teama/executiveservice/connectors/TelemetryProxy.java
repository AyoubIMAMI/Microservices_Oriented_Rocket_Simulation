package fr.teama.executiveservice.connectors;

import fr.teama.executiveservice.connectors.externalDTO.*;
import fr.teama.executiveservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.executiveservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.executiveservice.helpers.LoggerHelper;
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
    public ResponseEntity<String> missionStartNotify() throws TelemetryServiceUnavailableException {
        try {
            TrackItemDTO trackItemDTO = new TrackItemDTO(TrackingFieldDTO.HEIGHT, 0.0, OperationTypeDTO.LESS_OR_EQUAL);
            List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
            trackItemDTOList.add(trackItemDTO);

            TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "executive", TrackingCategoryDTO.INDEPENDENT_STAGE);
            LoggerHelper.logInfo("Ask telemetry to being notify when the stage 1 reach the height of " + trackItemDTO.getData());
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }
}
