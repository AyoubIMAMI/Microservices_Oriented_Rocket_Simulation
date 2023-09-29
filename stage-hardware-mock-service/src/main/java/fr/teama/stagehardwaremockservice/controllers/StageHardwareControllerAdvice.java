package fr.teama.stagehardwaremockservice.controllers;


import fr.teama.stagehardwaremockservice.controllers.dto.ErrorDTO;
import fr.teama.stagehardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {StageHardwareController.class})
public class StageHardwareControllerAdvice {

    @ExceptionHandler({TelemetryServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(TelemetryServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Telemetry service unavailable.");
        errorDTO.setDetails("The telemetry service is currently unavailable. Please try again later.");
        return errorDTO;
    }
}
