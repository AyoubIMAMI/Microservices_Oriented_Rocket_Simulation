package team.fr.webcasterservice.components;

import org.springframework.stereotype.Component;
import team.fr.webcasterservice.helpers.LoggerHelper;
import team.fr.webcasterservice.interfaces.IWebcaster;

@Component
public class WebcasterManager implements IWebcaster {


    @Override
    public void sayOnAir(String logs) {
        LoggerHelper.logInfo("Marie: 'The following news is whispered to me: " + logs + "'");
    }
}
