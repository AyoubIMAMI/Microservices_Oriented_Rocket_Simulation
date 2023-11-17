package fr.teama.scientificdepartmentservice.controllers;


import fr.teama.scientificdepartmentservice.interfaces.IRobotDepartmentProxy;
import fr.teama.scientificdepartmentservice.exception.RobotDepartmentServiceUnavailableException;
import fr.teama.scientificdepartmentservice.helpers.LoggerHelper;
import fr.teama.scientificdepartmentservice.models.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = ScientificDepartmentController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class ScientificDepartmentController {
    public static final String BASE_URI = "/api/scientific-department";

    @Autowired
    IRobotDepartmentProxy robotDepartmentProxy;

    @PostMapping("/robot-landed")
    public ResponseEntity<String> sendPositionToGo() throws RobotDepartmentServiceUnavailableException {
        LoggerHelper.logInfo("Warned that the robot has landed");
        robotDepartmentProxy.sendPositionToGo();
        return ResponseEntity.ok().body("Position sent to the robot department service");
    }
}
