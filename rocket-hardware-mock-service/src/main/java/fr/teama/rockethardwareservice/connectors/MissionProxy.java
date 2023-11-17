package fr.teama.rockethardwareservice.connectors;

import fr.teama.rockethardwareservice.exceptions.MissionServiceUnvailableException;
import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.proxy.IMissionProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MissionProxy implements IMissionProxy {
    @Value("${mission.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> warnMissionThatRocketHasCriticalAnomaly() throws MissionServiceUnvailableException {
        try {
            LoggerHelper.logWarn("Inform the mission service that the rocket has a critical anomaly and will self-destroy");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/mission/rocket-critical-anomaly", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new MissionServiceUnvailableException();
        }
    }
}
