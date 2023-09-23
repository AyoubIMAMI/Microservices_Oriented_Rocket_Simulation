package fr.teama.rocketdepartmentservice.connectors;

import fr.teama.rocketdepartmentservice.connectors.externalDTO.TrackItemDTO;
import fr.teama.rocketdepartmentservice.connectors.externalDTO.TrackingDTO;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.proxy.ITelemetryProxy;
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
    public void gettingNotifyWhenFuelIsEmpty() throws TelemetryServiceUnavailableException {
        try {
            TrackItemDTO trackItemDTO = new TrackItemDTO("fuel", 0.0);
            List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
            trackItemDTOList.add(trackItemDTO);
            TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "rocket-department");
            LoggerHelper.logInfo("Ask telemetry to being notify when the rocket reach the fuel of " + trackItemDTO.getData());
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new TelemetryServiceUnavailableException();
        }
    }
}