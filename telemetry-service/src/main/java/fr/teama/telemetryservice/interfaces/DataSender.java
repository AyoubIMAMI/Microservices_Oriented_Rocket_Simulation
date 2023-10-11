package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.controllers.dto.PayloadDataDTO;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface DataSender {
    ResponseEntity<String> sendPayloadData(PayloadDataDTO payloadDataDTO) throws PayloadServiceUnavailableException;
}
