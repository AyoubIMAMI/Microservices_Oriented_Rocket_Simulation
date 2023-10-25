package fr.teama.rockethardwareservice.connectors;

import fr.teama.rockethardwareservice.exceptions.RobotHardwareServiceUnavaibleException;

import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.proxy.IRobotHardwareProxy;
import fr.teama.rockethardwareservice.models.Position;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RobotHardwareProxy implements IRobotHardwareProxy {
    @Value("${robot-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void startRobotLogging(Position position) throws RobotHardwareServiceUnavaibleException {
        try {
            LoggerHelper.logInfo("Inform the robot hardware service to start logging");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/robot-hardware/start-logging", position, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            LoggerHelper.logError("Robot hardware service unavailable");
            throw new RobotHardwareServiceUnavaibleException();
        }
    }
}
