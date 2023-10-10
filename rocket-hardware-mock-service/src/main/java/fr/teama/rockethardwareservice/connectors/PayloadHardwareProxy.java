package fr.teama.rockethardwareservice.connectors;

import fr.teama.rockethardwareservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.helpers.LoggerHelper;
import fr.teama.rockethardwareservice.interfaces.proxy.IPayloadHardwareProxy;
import fr.teama.rockethardwareservice.models.Position;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PayloadHardwareProxy implements IPayloadHardwareProxy {
    @Value("${payload-hardware.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void startOrbitalPosDispatch(Position position) throws PayloadHardwareServiceUnavaibleException {
        try {
            LoggerHelper.logInfo("Inform the payload hardware service to start dispatching");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/payload-hardware/start-pos-dispatch", position, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            LoggerHelper.logError("Payload hardware service unavailable");
            throw new PayloadHardwareServiceUnavaibleException();
        }
    }
}
