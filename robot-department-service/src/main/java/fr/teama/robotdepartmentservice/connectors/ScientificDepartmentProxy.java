package fr.teama.robotdepartmentservice.connectors;

import fr.teama.robotdepartmentservice.exceptions.ScientificDepartmentServiceUnavailableException;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.proxy.IScientificDepartmentProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScientificDepartmentProxy implements IScientificDepartmentProxy {
    @Value("${scientific-department.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> askDestination() throws ScientificDepartmentServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Ask the scientific department service the destination");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/scientific-department/robot-landed", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Scientific department service unavailable");
            throw new ScientificDepartmentServiceUnavailableException();
        }
    }
}
