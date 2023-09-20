package fr.teama.rockethardwaremock.controllers;

import fr.teama.rockethardwaremock.interfaces.IHardware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@CrossOrigin
@RequestMapping(path = RocketHardwareController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class RocketHardwareController {

    public static final String BASE_URI = "/api/rocket-hardware";

    @Autowired
    private IHardware hardware;

    @PostMapping("/launched")
    public void rocketLaunched() {
        hardware.rocketLaunched();
    }
}
