package fr.teama.robotdepartmentservice.interfaces;

import fr.teama.robotdepartmentservice.exceptions.MissionServiceUnavailableException;
import fr.teama.robotdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IRobotReleaser {

    ResponseEntity<String> dropRobot() throws RocketHardwareServiceUnavailableException, MissionServiceUnavailableException;
}
