package fr.teama.rocketservice.components;

import fr.teama.rocketservice.helpers.LoggerHelper;
import fr.teama.rocketservice.interfaces.RocketSplitter;
import org.springframework.stereotype.Component;

@Component
public class RocketAction implements RocketSplitter {

    @Override
    public void stageRocket() {
        LoggerHelper.logInfo("The rocket as been staged");
    }
}
