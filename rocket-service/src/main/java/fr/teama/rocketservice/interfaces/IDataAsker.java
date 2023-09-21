package fr.teama.rocketservice.interfaces;

import fr.teama.rocketservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IDataAsker {
    void waitEmptyFuelForStageTheRocket() throws TelemetryServiceUnavailableException;
}
