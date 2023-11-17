package fr.teama.robotdepartmentservice.controllers;

import fr.teama.robotdepartmentservice.connectors.externalDTO.PositionDTO;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.IDataAsker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = RobotDepartmentController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class RobotDepartmentController {

    public static final String BASE_URI = "/api/robot";

    @Autowired
    private IDataAsker dataAsker;


    @PostMapping
    public ResponseEntity<String> missionStartWarning() {
        LoggerHelper.logInfo("Notification of the start of the mission");
        dataAsker.askDataToTelemetry();
        return ResponseEntity.ok("Mission start warning received");
    }

    @PostMapping("/destination")
    public void sendDestination(@RequestBody PositionDTO positionDTO) {
        LoggerHelper.logInfo("Sending destination to the robot");
        //TODO
    }

}
