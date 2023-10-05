package fr.teama.payloadservice.components;

import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.payloadservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import fr.teama.payloadservice.interfaces.proxy.IMissionProxy;
import fr.teama.payloadservice.interfaces.proxy.IPayloadHardwareProxy;
import fr.teama.payloadservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PayloadReleaser implements IPayloadReleaser {
    @Autowired
    IMissionProxy missionProxy;

    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @Override
    public ResponseEntity<String> dropPayload() throws TelemetryServiceUnavailableException, PayloadHardwareServiceUnavaibleException, RocketHardwareServiceUnavailableException {
        ResponseEntity<String> res = rocketHardwareProxy.dropPayload();
        missionProxy.missionSuccessNotify();
        return ResponseEntity.ok().body("Payload dropped");
    }
}
