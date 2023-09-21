package fr.teama.payloadservice.components;

import fr.teama.payloadservice.interfaces.IDataAsker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DataAsker implements IDataAsker {
    @Override
    public ResponseEntity<String> askOrbitToTelemetry() {
        return null;
    }
}
