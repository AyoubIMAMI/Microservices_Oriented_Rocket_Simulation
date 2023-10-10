package fr.teama.payloadservice.controllers;

import fr.teama.payloadservice.controllers.dto.PayloadDataDTO;
import fr.teama.payloadservice.controllers.dto.PositionDTO;
import fr.teama.payloadservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.payloadservice.models.PayloadData;
import fr.teama.payloadservice.models.Position;
import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.IDataAsker;
import fr.teama.payloadservice.interfaces.IPayloadReleaser;
import fr.teama.payloadservice.interfaces.PayloadDataHandler;
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
        LoggerHelper.logInfo("Notification of the start of the mission");
        return dataAsker.askOrbitToTelemetry();
    }

    @PostMapping("/drop")
    public ResponseEntity<String> dropPayload() throws TelemetryServiceUnavailableException, PayloadHardwareServiceUnavaibleException, RocketHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Notification received, requesting to drop the payload");
        return payloadReleaser.dropPayload();
    }
    @PostMapping("/data")
    public ResponseEntity<String> savePayloadData(@RequestBody PayloadDataDTO payloadDataDTO) throws PayloadHardwareServiceUnavaibleException {
        PositionDTO position= payloadDataDTO.getPosition();
        payloadDataHandler.saveDataPayload(new PayloadData(new Position(position.getX(), position.getY(),position.getAltitude()),payloadDataDTO.getTimestamp()));
        return ResponseEntity.status(HttpStatus.OK).body("Save successful");
    }

    @PostMapping("/reset-db")
    public ResponseEntity<String> resetDb() {
        LoggerHelper.logInfo("Resetting database");
        return this.payloadDataHandler.resetDB();
    }

}
