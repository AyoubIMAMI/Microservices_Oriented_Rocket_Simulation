package fr.teama.missionservice.interfaces;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

public interface IPollMaker {
    ResponseEntity<String> startMission();
}
