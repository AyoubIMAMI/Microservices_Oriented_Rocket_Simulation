package fr.teama.scientificdepartmentservice.controllers;


import fr.teama.scientificdepartmentservice.helpers.LoggerHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = ScientificDepartmentController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class ScientificDepartmentController {
    public static final String BASE_URI = "/api/rocket";

    @GetMapping("/coordinates")
    public ResponseEntity<String> getRocketCoordinates() {
        LoggerHelper.logInfo("Request received to send robot coordinates");
        return ResponseEntity.ok().body("OK");
    }
}
