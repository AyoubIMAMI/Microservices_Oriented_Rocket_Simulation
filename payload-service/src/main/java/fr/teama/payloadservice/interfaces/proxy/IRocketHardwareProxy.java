package fr.teama.payloadservice.interfaces.proxy;


import fr.teama.payloadservice.exceptions.RocketHardwareServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IRocketHardwareProxy {

    ResponseEntity<String> dropPayload() throws RocketHardwareServiceUnavailableException;

    ResponseEntity<String> activeEngine() throws RocketHardwareServiceUnavailableException;
}
