package fr.teama.telemetryservice.connectors;

import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.helpers.LoggerHelper;
import fr.teama.telemetryservice.interfaces.proxy.IPayloadProxy;
import fr.teama.telemetryservice.models.PayloadData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PayloadProxy implements IPayloadProxy {
    @Value("${payload.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public void heightReached() throws PayloadServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Notify payload service that the correct height has been reached");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/payload/drop", null, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new PayloadServiceUnavailableException();
        }
    }

    @Override
    public ResponseEntity<String> sendData(PayloadData payloadData) throws PayloadServiceUnavailableException {
        try {
            LoggerHelper.logInfo("Transferring the data from \u001B[37mpayload\u001B[32m hardware to the payload service");
            return restTemplate.postForEntity(apiBaseUrlHostAndPort + "/payload/data", payloadData, String.class);
        } catch (Exception e) {
            LoggerHelper.logError(e.toString());
            throw new PayloadServiceUnavailableException();
        }
    }
}
