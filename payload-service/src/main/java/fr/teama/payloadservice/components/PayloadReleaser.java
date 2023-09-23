package fr.teama.payloadservice.components;

import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.interfaces.ILoggerComponent;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import fr.teama.payloadservice.interfaces.proxy.IMissionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PayloadReleaser implements IPayloadReleaser {
    @Autowired
    ILoggerComponent logger;
    @Autowired
    IMissionProxy missionProxy;

    @Override
    public ResponseEntity<String> dropPayload() throws TelemetryServiceUnavailableException {
        logger.logInfo("The payload has been dropped");
        missionProxy.missionSuccessNotify();
        return ResponseEntity.ok().body("Payload dropped");
    }
}
