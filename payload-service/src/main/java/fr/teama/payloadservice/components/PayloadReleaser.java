package fr.teama.payloadservice.components;

import fr.teama.payloadservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import fr.teama.payloadservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PayloadReleaser implements IPayloadReleaser {

    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @Override
    public ResponseEntity<String> dropPayload() throws RocketHardwareServiceUnavailableException {
        rocketHardwareProxy.dropPayload();
        return ResponseEntity.ok().body("Payload dropped");
    }
}
