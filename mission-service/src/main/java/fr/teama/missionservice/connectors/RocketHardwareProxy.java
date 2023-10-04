package fr.teama.missionservice.connectors;

import fr.teama.missionservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.missionservice.helpers.LoggerHelper;
import fr.teama.missionservice.interfaces.proxy.IRocketHardwareProxy;
import fr.teama.missionservice.models.RocketData;
import fr.teama.missionservice.models.StageData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static java.lang.Thread.sleep;

@Component
public class RocketHardwareProxy implements IRocketHardwareProxy {
    @Value("${rocket-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void startLogging() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform the rocket hardware service to start logging");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/rocket-hardware/start-logging", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }

    @Override
    public void stopLogging() throws RocketHardwareServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Inform the rocket hardware service to stop logging");
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

    @Override
    public Double checkRocket() throws RocketHardwareServiceUnavailableException {
        try {
            ResponseEntity<RocketData> response = restTemplate.getForEntity(apiBaseUrlHostAndPort +
                    "/rocket-hardware/preparation", RocketData.class);
            RocketData rocketData = response.getBody();
            assert rocketData != null;

            // fuel level infos
            LoggerHelper.logInfo("Ask to the rocket hardware the stages fuel level");
            stagesDataLoggers(rocketData.getStages());
            LoggerHelper.logInfo("Fueling the rocket stages in progress");
            restTemplate.postForEntity(apiBaseUrlHostAndPort +
                    "/rocket-hardware/fueling", null, RocketData.class).getBody();
            LoggerHelper.logInfo("Fueling complete");
            LoggerHelper.logInfo("Ask to the rocket hardware the stages fuel level");
            rocketData = restTemplate.postForEntity(apiBaseUrlHostAndPort +
                    "/rocket-hardware/preparation", null, RocketData.class).getBody();
            assert rocketData != null;
            stagesDataLoggers(rocketData.getStages());

            // payload installation
            LoggerHelper.logInfo("Payload installation in progress");
            sleep(1000);
            LoggerHelper.logInfo("Payload installation complete");

            // status info
            Double rocketStatus = rocketData.getStatus();
            if (rocketStatus == 1.0) {
                LoggerHelper.logInfo("Rocket status " + rocketData.getStatus() + " - OK");
            } else {
                LoggerHelper.logWarn("Rocket status " + rocketData.getStatus() + " - NOT OK");
            }

            return rocketStatus;
        } catch (Exception e) {
            LoggerHelper.logError("Hardware service is unavailable");
            throw new RocketHardwareServiceUnavailableException();
        }
    }

    void stagesDataLoggers(List<StageData> stages) {
        for (StageData stage : stages) {
            LoggerHelper.logInfo("Rocket stage " + stage.getStageLevel() + " - fuel level: " + stage.getFuel());
        }
    }

}
