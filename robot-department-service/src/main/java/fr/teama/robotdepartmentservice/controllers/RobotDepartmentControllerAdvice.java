package fr.teama.robotdepartmentservice.controllers;

import fr.teama.robotdepartmentservice.controllers.dto.ErrorDTO;
import fr.teama.robotdepartmentservice.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {RobotDepartmentController.class})
public class RobotDepartmentControllerAdvice {


    @ExceptionHandler({MissionServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(MissionServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Mission service unavailable.");
        errorDTO.setDetails("The mission service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({RocketHardwareServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(RocketHardwareServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Rocket hardware service unavailable.");
        errorDTO.setDetails("The rocket hardware service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({RobotHardwareServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(RobotHardwareServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Robot hardware service unavailable.");
        errorDTO.setDetails("The robot hardware service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({ScientificDepartmentServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(ScientificDepartmentServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Scientific department service unavailable.");
        errorDTO.setDetails("The scientific department service is currently unavailable. Please try again later.");
        return errorDTO;
    }

}
