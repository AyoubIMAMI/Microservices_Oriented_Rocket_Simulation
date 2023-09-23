package fr.teama.rocketservice.components;

import fr.teama.rocketservice.interfaces.ILoggerComponent;
import fr.teama.rocketservice.interfaces.RocketSplitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RocketAction implements RocketSplitter {
    @Autowired
    ILoggerComponent logger;

    @Override
    public void stageRocket() {
        logger.logInfo("The rocket as been staged");
    }
}
