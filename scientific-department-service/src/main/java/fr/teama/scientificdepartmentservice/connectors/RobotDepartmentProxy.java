package fr.teama.scientificdepartmentservice.connectors;

import fr.teama.scientificdepartmentservice.interfaces.IRobotDepartmentProxy;
import fr.teama.scientificdepartmentservice.models.Position;
import fr.teama.scientificdepartmentservice.exception.RobotDepartmentServiceUnavailableException;
import fr.teama.scientificdepartmentservice.helpers.LoggerHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RobotDepartmentProxy implements IRobotDepartmentProxy {
    @Value("${robot-department.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendPositionToGo() throws RobotDepartmentServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Sending to the robot department service the position where the robot has to go to");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/robot/destination", new Position(12.0, 42.0, 1.0), Position.class);
        } catch (Exception e) {
            throw new RobotDepartmentServiceUnavailableException();
        }
    }
}
