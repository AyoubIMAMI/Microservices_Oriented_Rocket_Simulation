package fr.teama.payloadservice.controllers;

import fr.teama.payloadservice.controllers.dto.ErrorDTO;
import fr.teama.payloadservice.exceptions.PayloadHardwareServiceUnavaibleException;
import fr.teama.payloadservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.payloadservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.payloadservice.exceptions.WebcasterServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {PayloadController.class})
public class PayloadControllerAdvice {

    @ExceptionHandler({TelemetryServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(TelemetryServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Telemetry service unavailable.");
        errorDTO.setDetails("The telemetry service is currently unavailable. Please try again later.");
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

    @ExceptionHandler({PayloadHardwareServiceUnavaibleException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(PayloadHardwareServiceUnavaibleException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Payload hardware service unavailable.");
        errorDTO.setDetails("The payload hardware service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({WebcasterServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(WebcasterServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Payload hardware service unavailable.");
        errorDTO.setDetails("The payload hardware service is currently unavailable. Please try again later.");
        return errorDTO;
    }

}
