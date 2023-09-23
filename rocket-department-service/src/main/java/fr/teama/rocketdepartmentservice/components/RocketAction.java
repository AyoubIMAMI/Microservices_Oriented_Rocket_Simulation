package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.RocketSplitter;
import org.springframework.stereotype.Component;

@Component
public class RocketAction implements RocketSplitter {

    @Override
    public void stageRocket() {
        LoggerHelper.logInfo("The rocket as been staged");
    }
}
