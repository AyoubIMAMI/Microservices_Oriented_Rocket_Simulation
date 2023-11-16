package fr.teama.robotdepartmentservice.controllers;

import fr.teama.robotdepartmentservice.services.KafkaProducerService;
import fr.teama.robotdepartmentservice.exceptions.*;
import fr.teama.robotdepartmentservice.helpers.LoggerHelper;
import fr.teama.robotdepartmentservice.interfaces.IDataAsker;
import fr.teama.robotdepartmentservice.interfaces.IRobotManager;
import fr.teama.robotdepartmentservice.interfaces.IRobotReleaser;
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

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private IRobotReleaser robotReleaser;

    @Autowired
    private IRobotManager robotManager;


    @PostMapping
    public ResponseEntity<String> missionStartWarning() {
        LoggerHelper.logInfo("Notification of the start of the mission");
        dataAsker.askDataToTelemetry();
        return ResponseEntity.ok("Mission start warning received");
    }

    @PostMapping("/drop")
    public ResponseEntity<String> dropRobot() throws RocketHardwareServiceUnavailableException, MissionServiceUnavailableException {
        LoggerHelper.logInfo("Notification received, requesting to drop the robot");
        kafkaProducerService.warnWebcaster("Robot drop requested");
        return robotReleaser.dropRobot();
    }

    @PostMapping("/landed")
    public ResponseEntity<String> landed() throws RobotHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Notification received, robot has landed");
        kafkaProducerService.warnWebcaster("Robot has landed");
        robotManager.startRobot();


        return ResponseEntity.ok("Robot has landed");
    }

    @PostMapping("/reached-position")
    public ResponseEntity<String> reachedPosition() throws RobotHardwareServiceUnavailableException {
        LoggerHelper.logInfo("Notification received, robot has reached the position");
        kafkaProducerService.warnWebcaster("Robot has reached the position");
        robotManager.takeSamples();

        return ResponseEntity.ok("Robot has reached the position");
    }

}
