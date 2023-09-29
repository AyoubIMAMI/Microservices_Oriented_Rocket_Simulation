package fr.teama.payloadservice.interfaces;

import fr.teama.payloadservice.entities.PayloadData;
import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import org.springframework.http.ResponseEntity;

public interface PayloadDataHandler {
    void saveDataPayload(PayloadData payloadData) throws PayloadHardwareServiceUnavaibleException;

    ResponseEntity<String> resetDB();

}
