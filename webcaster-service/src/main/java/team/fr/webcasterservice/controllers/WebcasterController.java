package team.fr.webcasterservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = WebcasterController.BASE_URI, produces = APPLICATION_JSON_VALUE)
public class WebcasterController {
    public static final String BASE_URI = "/api/webcaster";


}
