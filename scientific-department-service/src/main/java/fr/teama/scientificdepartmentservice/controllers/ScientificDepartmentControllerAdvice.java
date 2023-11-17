package fr.teama.scientificdepartmentservice.controllers;

import fr.teama.scientificdepartmentservice.controllers.dto.ErrorDTO;
import fr.teama.scientificdepartmentservice.exception.RobotDepartmentServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {ScientificDepartmentController.class})
public class ScientificDepartmentControllerAdvice {

    @ExceptionHandler({RobotDepartmentServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(RobotDepartmentServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Robot department service unavailable.");
        errorDTO.setDetails("The Robot Department service is currently unavailable. Please try again later.");
        return errorDTO;
    }
}
