package fr.teama.scientificdepartmentservice.controllers;


import fr.teama.scientificdepartmentservice.IRobotDepartmentProxy;
import fr.teama.scientificdepartmentservice.entities.Position;
import fr.teama.scientificdepartmentservice.exception.RobotDepartmentServiceUnavailableException;
import fr.teama.scientificdepartmentservice.helpers.LoggerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = ScientificDepartmentController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class ScientificDepartmentController {
    public static final String BASE_URI = "/api/rocket";

    @Autowired
    IRobotDepartmentProxy robotDepartmentProxy;

    @PostMapping("/robot-landed")
    public ResponseEntity<String> sendPositionToGo() throws RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Warned that the robot has landed");
        robotDepartmentProxy.sendPositionToGo();
        return ResponseEntity.ok().body("Position sent to the robot department service");
    }

    //TODO receive sample to analyze them
}
