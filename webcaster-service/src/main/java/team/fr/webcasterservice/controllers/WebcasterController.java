package team.fr.webcasterservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = WebcasterController.BASE_URI)
public class WebcasterController {
    public static final String BASE_URI = "/api/webcaster";
}
