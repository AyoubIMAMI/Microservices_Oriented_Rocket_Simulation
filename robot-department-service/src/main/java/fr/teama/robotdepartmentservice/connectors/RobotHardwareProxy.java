package fr.teama.robotdepartmentservice.connectors;

import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.proxy.IRobotHardwareProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RobotHardwareProxy implements IRobotHardwareProxy {
    @Value("${robot-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> stopLogging() throws RobotHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform the robot hardware service to stop logging");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/robot-hardware/stop-logging", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RobotHardwareServiceUnavailableException();
        }
    }
}
