package fr.teama.stagehardwaremockservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@CrossOrigin
@RequestMapping(path = StageHardwareController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class StageHardwareController {

    public static final String BASE_URI = "/api/stage-hardware";



}
