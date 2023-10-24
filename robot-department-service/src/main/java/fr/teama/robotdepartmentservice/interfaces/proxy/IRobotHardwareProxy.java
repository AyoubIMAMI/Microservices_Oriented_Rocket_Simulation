package fr.teama.robotdepartmentservice.interfaces.proxy;


import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IRobotHardwareProxy {

    ResponseEntity<String> stopLogging() throws RobotHardwareServiceUnavailableException;

}
