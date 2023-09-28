package fr.teama.payloadservice.controllers;

import fr.teama.payloadservice.controllers.dto.PayloadDataDTO;
import fr.teama.payloadservice.controllers.dto.PositionDTO;
import fr.teama.payloadservice.entities.PayloadData;
import fr.teama.payloadservice.entities.Position;
import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.IDataAsker;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import fr.teama.payloadservice.repository.PayloadDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = PayloadController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class PayloadController {

    public static final String BASE_URI = "/api/payload";
    @Autowired
    private PayloadDataRepository payloadDataRepository;

    @Autowired
    private IPayloadReleaser payloadReleaser;

    @Autowired
    private IDataAsker dataAsker;

    @PostMapping
    public ResponseEntity<String> missionStartWarning() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("The mission has started");
        return dataAsker.askOrbitToTelemetry();
    }

    @PostMapping("/drop")
    public ResponseEntity<String> dropPayload() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("Request for dropping the payload");
        return payloadReleaser.dropPayload();
    }
    @PostMapping("/data")
    public ResponseEntity<String> savePayloadData(PayloadDataDTO payloadDataDTO) {
        LoggerHelper.logInfo("Saving the payload");
        PositionDTO position= payloadDataDTO.getPosition();
        payloadDataRepository.save(new PayloadData(new Position(position.getX(), position.getY(),position.getAltitude()),payloadDataDTO.getTimestamp()));
        return ResponseEntity.status(HttpStatus.OK).body("Save successful");
    }

}
