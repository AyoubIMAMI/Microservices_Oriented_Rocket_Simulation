package fr.teama.telemetryservice.connectors;

import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.interfaces.ILoggerComponent;
import fr.teama.telemetryservice.interfaces.proxy.IPayloadProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PayloadProxy implements IPayloadProxy {
    @Value("${payload.host.baseurl}")
    private String apiBaseUrlHostAndPort;

    @Autowired
    ILoggerComponent logger;

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public void heightReached() throws PayloadServiceUnavailableException {
        try {
            logger.logInfo("Notify payload service that the correct height has been reached");
            restTemplate.postForEntity(apiBaseUrlHostAndPort + "/payload/drop", null, String.class);
        } catch (Exception e) {
            throw new PayloadServiceUnavailableException();
        }
    }
}
