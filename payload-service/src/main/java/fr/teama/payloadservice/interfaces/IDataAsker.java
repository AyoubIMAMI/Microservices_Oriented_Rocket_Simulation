package fr.teama.payloadservice.interfaces;

import org.springframework.http.ResponseEntity;

public interface IDataAsker {

    ResponseEntity<String> askOrbitToTelemetry();
}
