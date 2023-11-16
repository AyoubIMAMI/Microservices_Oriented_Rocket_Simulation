package fr.teama.executiveservice.controllers;

import fr.teama.executiveservice.controllers.dto.ErrorDTO;
import fr.teama.executiveservice.exceptions.LogsServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {ExecutiveController.class})
public class ExecutiveControllerAdvice {

    @ExceptionHandler({LogsServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(LogsServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Logs service unavailable.");
        errorDTO.setDetails("The logs service is currently unavailable. Please try again later.");
        return errorDTO;
    }

}
