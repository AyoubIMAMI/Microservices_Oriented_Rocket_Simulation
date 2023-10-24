package fr.teama.telemetryservice.connectors;

import fr.teama.telemetryservice.exceptions.RobotDepartmentServiceUnavailableException;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.proxy.IRobotDepartmentProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RobotDepartmentProxy implements IRobotDepartmentProxy {

    @Value("${robot-department.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void notifyHeightReached() throws RobotDepartmentServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Notify the robot department that the height has reached a specific level");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/robot/drop", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new RobotDepartmentServiceUnavailableException();
        }
    }

    @Override
    public void landedSuccessfully() throws RobotDepartmentServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Notify the robot department that the robot has landed successfully");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/robot/landed", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new RobotDepartmentServiceUnavailableException();
        }
    }
}
