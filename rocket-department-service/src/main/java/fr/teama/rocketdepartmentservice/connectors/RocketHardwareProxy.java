package fr.teama.rocketdepartmentservice.connectors;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.proxy.IRocketHardwareProxy;
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
    public void stageRocket() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform the rocket hardware service to stage the rocket");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/stage", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }

    @Override
    public void activateCurrentStage() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform the rocket hardware service to activate the current stage");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/activate-stage", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }
}
