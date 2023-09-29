package fr.teama.telemetryservice.controllers;


import fr.teama.telemetryservice.controllers.dto.ErrorDTO;
import fr.teama.telemetryservice.exceptions.ExecutiveServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.MissionServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {TelemetryController.class})
public class TelemetryControllerAdvice {

    @ExceptionHandler({ExecutiveServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(ExecutiveServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Executive service unavailable.");
        errorDTO.setDetails("The executive service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({MissionServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(MissionServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Mission service unavailable.");
        errorDTO.setDetails("The mission service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({PayloadServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(PayloadServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Payload service unavailable.");
        errorDTO.setDetails("The payload service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({RocketStageServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(RocketStageServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Rocket stage service unavailable.");
        errorDTO.setDetails("The rocket stage service is currently unavailable. Please try again later.");
        return errorDTO;
    }
}
