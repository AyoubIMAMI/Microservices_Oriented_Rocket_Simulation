package fr.teama.executiveservice.interfaces;

import fr.teama.executiveservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IDataAsker {

    ResponseEntity<String> askStageHeightToTelemetry() throws TelemetryServiceUnavailableException;
}
