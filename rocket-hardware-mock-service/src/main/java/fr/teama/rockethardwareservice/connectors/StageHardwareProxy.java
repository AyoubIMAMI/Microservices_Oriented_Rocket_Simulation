package fr.teama.rockethardwareservice.connectors;

import fr.teama.rockethardwareservice.connectors.externalDTO.StageFullStateDTO;
import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.proxy.IStageHardwareProxy;
import fr.teama.rockethardwareservice.models.Position;
import fr.teama.rockethardwareservice.models.StageData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class StageHardwareProxy implements IStageHardwareProxy {
    @Value("${stage-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void startLogging(StageData stageToDetach, Position position, Double speed) throws StageHardwareServiceUnavailableException {
        StageFullStateDTO stageFullStateDTO = new StageFullStateDTO(stageToDetach, position, speed);
        LoggerHelper.logInfo("Send full stage data to stage hardware service : " + stageFullStateDTO);
        try {
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/stage-hardware/start-logging", stageFullStateDTO, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new StageHardwareServiceUnavailableException();
        }
    }
}
