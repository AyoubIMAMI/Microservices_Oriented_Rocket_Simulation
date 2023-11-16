package fr.teama.rockethardwareservice.controllers;

import fr.teama.rockethardwareservice.controllers.dto.ErrorDTO;
import fr.teama.rockethardwareservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.exceptions.RobotHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.exceptions.StageHardwareServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {RocketHardwareController.class})
public class RocketHardwareControllerAdvice {

    @ExceptionHandler({StageHardwareServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(StageHardwareServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Stage hardware service unavailable.");
        errorDTO.setDetails("The stage hardware service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({PayloadHardwareServiceUnavaibleException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(PayloadHardwareServiceUnavaibleException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Payload hardware service unavailable.");
        errorDTO.setDetails("The payload hardware service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({RobotHardwareServiceUnavaibleException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(RobotHardwareServiceUnavaibleException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Robot hardware service unavailable.");
        errorDTO.setDetails("The robot hardware service is currently unavailable. Please try again later.");
        return errorDTO;
    }
}
