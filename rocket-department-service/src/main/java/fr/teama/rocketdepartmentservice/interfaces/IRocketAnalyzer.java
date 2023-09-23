package fr.teama.rocketdepartmentservice.interfaces;


import org.springframework.http.ResponseEntity;

public interface IRocketAnalyzer {

    ResponseEntity<String> getRocketStatus();
}
