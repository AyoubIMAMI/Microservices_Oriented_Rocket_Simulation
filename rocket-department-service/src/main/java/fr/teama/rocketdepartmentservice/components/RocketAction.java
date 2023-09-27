package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.RocketManager;
import fr.teama.rocketdepartmentservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RocketAction implements RocketManager {

    @Autowired
    private IRocketHardwareProxy rocketHardwareProxy;

    @Override
    public void stageRocket() {
        LoggerHelper.logInfo("The rocket has been staged");
    }

    @Override
    public void slowDownRocket() {
        LoggerHelper.logInfo("The rocket is slowing down");
        rocketHardwareProxy.slowDown();
    }

    @Override
    public void speedUpRocket() {
        LoggerHelper.logInfo("The rocket is speeding up");
        rocketHardwareProxy.speedUp();
    }
}
