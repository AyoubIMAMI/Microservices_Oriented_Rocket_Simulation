package fr.teama.rocketservice.connectors;

import fr.teama.rocketservice.connectors.externalDTO.TrackItemDTO;
import fr.teama.rocketservice.connectors.externalDTO.TrackingDTO;
import fr.teama.rocketservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketservice.interfaces.proxy.ITelemetryProxy;
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
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            throw new TelemetryServiceUnavailableException();
        }
    }
}
