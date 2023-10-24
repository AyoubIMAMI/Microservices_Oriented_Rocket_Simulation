package fr.teama.robotdepartmentservice.interfaces.proxy;


import fr.teama.robotdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IRocketHardwareProxy {

    ResponseEntity<String> dropRobot() throws RocketHardwareServiceUnavailableException;

}
