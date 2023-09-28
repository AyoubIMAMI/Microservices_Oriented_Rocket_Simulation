package fr.teama.payloadservice.connectors;

import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.proxy.IPayloadHardwareProxy;
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
    public void startOrbitalPosDispatch() throws PayloadHardwareServiceUnavaibleException {
        try {
            LoggerHelper.logInfo("Warn the payload hardware service to start dispatching");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/payload-hardware/start-pos-dispatch", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            LoggerHelper.logError("Payload hardware service unavailable");
            throw new PayloadHardwareServiceUnavaibleException();
        }
    }

    @Override
    public void stopOrbitalPosDispatch() throws PayloadHardwareServiceUnavaibleException {
        try {
            LoggerHelper.logInfo("Warn the payload hardware service to stop dispatching");
            ResponseEntity<String> response = restTemplate.postForEntity(apiBaseUrlHostAndPort + "/payload-hardware/stop-pos-dispatch", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError("Payload hardware service unavailable");
            throw new PayloadHardwareServiceUnavaibleException();
        }
    }
}
