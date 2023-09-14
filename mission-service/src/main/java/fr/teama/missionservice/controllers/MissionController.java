package fr.teama.missionservice.controllers;

import fr.teama.missionservice.interfaces.IPollMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MissionController {
    public static final String BASE_URI = "/api/mission";

    private final IPollMaker pollMaker;

    @Autowired
    public MissionController(IPollMaker pollMaker) {
        this.pollMaker = pollMaker;
    }

    @PostMapping(path = BASE_URI + "/poll")
    public ResponseEntity<String> startMission() {
        return pollMaker.startMission();
    }
}
