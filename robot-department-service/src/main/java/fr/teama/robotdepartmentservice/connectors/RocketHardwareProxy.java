package fr.teama.robotdepartmentservice.connectors;

import fr.teama.robotdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RocketHardwareProxy implements IRocketHardwareProxy {
    @Value("${rocket-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> dropRobot() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform the rocket hardware service to drop the robot");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/drop-robot", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }
}
