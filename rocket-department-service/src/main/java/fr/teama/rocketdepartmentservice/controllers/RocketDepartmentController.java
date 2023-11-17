package fr.teama.rocketdepartmentservice.controllers;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAnalyzer;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = RocketDepartmentController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class RocketDepartmentController {
    public static final String BASE_URI = "/api/rocket";

    @Autowired
    private IRocketAnalyzer rocketAnalyzer;

    @Autowired
    private IRocketAction rocketAction;


    @GetMapping("/status")
    public ResponseEntity<String> getRocketStatus() {
        LoggerHelper.logInfo("Request received to send rocket status");
        return rocketAnalyzer.getRocketStatus();
    }

    @PostMapping("/launch")
    public ResponseEntity<String> startRocket() throws RocketHardwareServiceUnavailableException, InterruptedException {
        LoggerHelper.logInfo("Request received to launch the rocket in 60 seconds");
        rocketAction.launchRocket();
        return ResponseEntity.ok().body("OK");
    }
}
