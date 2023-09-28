package fr.teama.executiveservice.controllers;

import fr.teama.executiveservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.executiveservice.helpers.LoggerHelper;
import fr.teama.executiveservice.interfaces.IDataAsker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = ExecutiveController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class ExecutiveController {

    public static final String BASE_URI = "/api/executive";

    @Autowired
    private IDataAsker dataAsker;

    @PostMapping
    public ResponseEntity<String> missionStartWarning() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("The mission has started");
        return dataAsker.askStageHeightToTelemetry();
    }

}
