package fr.teama.payloadservice.controllers;

import fr.teama.payloadservice.controllers.dto.PayloadDataDTO;
import fr.teama.payloadservice.controllers.dto.PositionDTO;
import fr.teama.payloadservice.entities.PayloadData;
import fr.teama.payloadservice.entities.Position;
import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.IDataAsker;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import fr.teama.payloadservice.interfaces.PayloadDataHandler;
import fr.teama.payloadservice.repository.PayloadDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = PayloadController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class PayloadController {

    public static final String BASE_URI = "/api/payload";

    @Autowired
    private IPayloadReleaser payloadReleaser;
    @Autowired
    private IDataAsker dataAsker;
    @Autowired
    private PayloadDataHandler payloadDataHandler;


    @PostMapping
    public ResponseEntity<String> missionStartWarning() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("The mission has started");
        return dataAsker.askOrbitToTelemetry();
    }

    @PostMapping("/drop")
    public ResponseEntity<String> dropPayload() throws TelemetryServiceUnavailableException, PayloadHardwareServiceUnavaibleException {
        LoggerHelper.logInfo("Request for dropping the payload");
        return payloadReleaser.dropPayload();
    }
    @PostMapping("/data")
    public ResponseEntity<String> savePayloadData(@RequestBody PayloadDataDTO payloadDataDTO) throws PayloadHardwareServiceUnavaibleException {
        PositionDTO position= payloadDataDTO.getPosition();
        payloadDataHandler.saveDataPayload(new PayloadData(new Position(position.getX(), position.getY(),position.getAltitude()),payloadDataDTO.getTimestamp()));
        return ResponseEntity.status(HttpStatus.OK).body("Save successful");
    }

}
