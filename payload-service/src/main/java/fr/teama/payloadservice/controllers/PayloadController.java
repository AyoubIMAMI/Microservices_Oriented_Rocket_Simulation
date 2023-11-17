package fr.teama.payloadservice.controllers;

import fr.teama.payloadservice.helpers.LoggerHelper;
import fr.teama.payloadservice.interfaces.PayloadDataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = PayloadController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class PayloadController {

    public static final String BASE_URI = "/api/payload";

    @Autowired
    private PayloadDataHandler payloadDataHandler;


    @PostMapping("/reset-db")
    public ResponseEntity<String> resetDb() {
        LoggerHelper.logInfo("Resetting database");
        return this.payloadDataHandler.resetDB();
    }

}
