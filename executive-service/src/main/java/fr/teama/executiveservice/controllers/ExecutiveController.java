package fr.teama.executiveservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = ExecutiveController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class ExecutiveController {

    public static final String BASE_URI = "/api/executive";

}
