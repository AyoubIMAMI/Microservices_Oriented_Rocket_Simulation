package fr.teama.robotdepartmentservice.interfaces.proxy;


import fr.teama.robotdepartmentservice.exceptions.ScientificDepartmentServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface IScientificDepartmentProxy {

    ResponseEntity<String> askDestination() throws ScientificDepartmentServiceUnavailableException;

}
