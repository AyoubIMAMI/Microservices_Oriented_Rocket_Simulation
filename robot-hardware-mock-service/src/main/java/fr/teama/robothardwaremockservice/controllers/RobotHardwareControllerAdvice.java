package fr.teama.robothardwaremockservice.controllers;


import fr.teama.robothardwaremockservice.controllers.dto.ErrorDTO;
import fr.teama.robothardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {RobotHardwareController.class})
public class RobotHardwareControllerAdvice {

    @ExceptionHandler({TelemetryServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(TelemetryServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Telemetry service unavailable.");
        errorDTO.setDetails("The telemetry service is currently unavailable. Please try again later.");
        return errorDTO;
    }
}
