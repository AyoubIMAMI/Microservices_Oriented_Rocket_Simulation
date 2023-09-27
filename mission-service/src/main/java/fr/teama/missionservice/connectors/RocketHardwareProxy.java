package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class RocketHardwareProxy implements IRocketHardwareProxy {
    @Value("${rocket-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void startLogging() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Warn the rocket hardware service to start logging");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/start-logging", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }

    @Override
    public void stopLogging() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Warn the rocket hardware service to stop logging");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/stop-logging", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }

    @Override
    public void rocketDestruction() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Order the rocket hardware to destroy the rocket");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/destroy", null, String.class);
            if (Objects.equals(response.getBody(), "OK"))
                LoggerHelper.logInfo("The rocket hardware has been destroyed");
            else
                LoggerHelper.logWarn("The rocket hardware has not been able to be destroyed");
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service is unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }
}
