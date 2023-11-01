package fr.teama.robotdepartmentservice.connectors;

import fr.teama.robotdepartmentservice.connectors.externalDTO.PositionDTO;
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
    @Override
    public ResponseEntity<String> moveToTheSpot(double x, double y) throws RobotHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Telling to the robot hardware service to use autopilot to move to the spot");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/robot-hardware/start-movement", new PositionDTO(x,y), String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RobotHardwareServiceUnavailableException();
        }
    }

    @Override
    public ResponseEntity<String> takeSamples() throws RobotHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Telling to the robot hardware service to take samples");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/robot-hardware/take-samples", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RobotHardwareServiceUnavailableException();
        }
    }
}
