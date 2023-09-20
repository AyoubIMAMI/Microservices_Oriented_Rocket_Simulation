package fr.teama.missionservice.controllers;

import fr.teama.missionservice.controllers.dto.ErrorDTO;
import fr.teama.missionservice.exceptions.RocketServiceUnavailableException;
import fr.teama.missionservice.exceptions.WeatherServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {MissionController.class})
public class MissionControllerAdvice {

    @ExceptionHandler({WeatherServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(WeatherServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Weather service unavailable.");
        errorDTO.setDetails("The weather service is currently unavailable. Please try again later.");
        return errorDTO;
    }

    @ExceptionHandler({RocketServiceUnavailableException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorDTO handleExceptions(RocketServiceUnavailableException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setError("Rocket service unavailable.");
        errorDTO.setDetails("The rocket service is currently unavailable. Please try again later.");
        return errorDTO;
    }
}
