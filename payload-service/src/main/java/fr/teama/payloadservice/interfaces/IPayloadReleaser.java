package fr.teama.payloadservice.interfaces;

import org.springframework.http.ResponseEntity;

public interface IPayloadReleaser {

    ResponseEntity<String> dropPayload();
}
