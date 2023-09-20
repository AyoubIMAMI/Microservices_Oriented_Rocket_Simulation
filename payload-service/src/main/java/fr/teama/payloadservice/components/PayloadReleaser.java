package fr.teama.payloadservice.components;

import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PayloadReleaser implements IPayloadReleaser {

    @Override
    public ResponseEntity<String> dropPayload() {
        return ResponseEntity.ok().body("Payload dropped");
    }
}
