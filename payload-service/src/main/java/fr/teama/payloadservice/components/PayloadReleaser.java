package fr.teama.payloadservice.components;

import fr.teama.payloadservice.interfaces.ILoggerComponent;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PayloadReleaser implements IPayloadReleaser {
    @Autowired
    ILoggerComponent logger;

    @Override
    public ResponseEntity<String> dropPayload() {
        logger.logInfo("The payload has been dropped");
        return ResponseEntity.ok().body("Payload dropped");
    }
}
