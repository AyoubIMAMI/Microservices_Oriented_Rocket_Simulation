package fr.teama.robotdepartmentservice.connectors;

import fr.teama.robotdepartmentservice.exceptions.MissionServiceUnavailableException;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.proxy.IMissionProxy;
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
    public ResponseEntity<String> missionSuccessNotify() throws MissionServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Notify mission-service that the robot has been dropped and the mission is a success");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/mission/success", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new MissionServiceUnavailableException();
        }
    }

}
