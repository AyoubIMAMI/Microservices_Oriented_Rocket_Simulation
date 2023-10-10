package team.fr.webcasterservice.controllers;

import org.springframework.web.bind.annotation.*;
import team.fr.webcasterservice.helpers.LoggerHelper;

@RestController
@CrossOrigin
@RequestMapping(path = WebcasterController.BASE_URI)
public class WebcasterController {
    public static final String BASE_URI = "/api/webcaster";

    @PostMapping
    void receiveLogs(@RequestBody String logs) {
        LoggerHelper.logInfo("Marie: 'The following news is whispered to me: " + logs + "'");
    }
}
