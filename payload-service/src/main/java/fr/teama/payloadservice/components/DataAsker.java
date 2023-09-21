package fr.teama.payloadservice.components;

import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.interfaces.IDataAsker;
import fr.teama.payloadservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DataAsker implements IDataAsker {

    @Autowired
    private ITelemetryProxy telemetryProxy;
    @Override
    public ResponseEntity<String> askOrbitToTelemetry() throws TelemetryServiceUnavailableException {
        return telemetryProxy.missionStartNotify();
    }
}
