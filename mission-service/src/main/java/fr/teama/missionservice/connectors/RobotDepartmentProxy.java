package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.RobotDepartmentServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.IRobotDepartmentProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RobotDepartmentProxy implements IRobotDepartmentProxy {

    @Value("${robot-department.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void missionStartNotification() throws RobotDepartmentServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform robot department service that the mission has started");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/robot", null, String.class);
        } catch (Exception e) {
            throw new RobotDepartmentServiceUnavailableException();
        }
    }
}
