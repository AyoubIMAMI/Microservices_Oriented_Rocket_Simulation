package fr.teama.logsservice.controllers;

import fr.teama.logsservice.controllers.dto.MissionLogDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = LogsController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class LogsController {
    public static final String BASE_URI = "/api/logs";

    @PostMapping("/save")
    public ResponseEntity<String> saveNewLog(@RequestBody MissionLogDTO missionLogDTO) {
        return ResponseEntity.ok().body("Log saved");
    }
}
