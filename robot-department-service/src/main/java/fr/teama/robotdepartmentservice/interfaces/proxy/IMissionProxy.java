package fr.teama.robotdepartmentservice.interfaces.proxy;

import fr.teama.robotdepartmentservice.exceptions.MissionServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IMissionProxy {

    ResponseEntity<String> missionSuccessNotify() throws MissionServiceUnavailableException;
}
