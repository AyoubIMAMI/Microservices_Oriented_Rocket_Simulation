package fr.teama.payloadservice.interfaces;

import fr.teama.payloadservice.exceptions.MissionServiceUnavailableException;
import fr.teama.payloadservice.exceptions.RocketHardwareServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IPayloadReleaser {

    ResponseEntity<String> dropPayload() throws RocketHardwareServiceUnavailableException, MissionServiceUnavailableException;
}
