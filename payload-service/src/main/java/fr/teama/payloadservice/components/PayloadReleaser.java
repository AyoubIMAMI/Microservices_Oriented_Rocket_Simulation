package fr.teama.payloadservice.components;

import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import fr.teama.payloadservice.interfaces.proxy.IMissionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class PayloadReleaser implements IPayloadReleaser {
    @Autowired
    IMissionProxy missionProxy;

    @Override
    public ResponseEntity<String> dropPayload() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("The payload has been dropped");
        missionProxy.missionSuccessNotify();
        return ResponseEntity.ok().body("Payload dropped");
    }
}
