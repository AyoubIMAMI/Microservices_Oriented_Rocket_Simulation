package fr.teama.payloadservice.connectors;

import fr.teama.payloadservice.connectors.externalDTO.TrackItemDTO;
import fr.teama.payloadservice.connectors.externalDTO.TrackingDTO;
import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.interfaces.ILoggerComponent;
import fr.teama.payloadservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ILoggerComponent logger;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> missionStartNotify() throws TelemetryServiceUnavailableException {
        try {
            TrackItemDTO trackItemDTO = new TrackItemDTO("height", 3600.0);
            List<TrackItemDTO> trackItemDTOList = new ArrayList<>();
            trackItemDTOList.add(trackItemDTO);

            TrackingDTO trackingDTO = new TrackingDTO(trackItemDTOList, "payload");
            logger.logInfo("Ask telemetry to being notify when the rocket reach the height of " + trackItemDTO.getData());
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/telemetry/tracking", trackingDTO, String.class);
        } catch (Exception e) {
            System.out.println(e);
            throw new TelemetryServiceUnavailableException();
        }
    }
}
