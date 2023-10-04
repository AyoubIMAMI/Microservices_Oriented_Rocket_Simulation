package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IDataAsker;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAction;
import fr.teama.rocketdepartmentservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Component
public class RocketAction implements IRocketAction {

    @Autowired
    private IRocketHardwareProxy rocketHardwareProxy;

    @Autowired
    private IDataAsker dataAsker;

    @Override
    public void stageRocket() throws RocketHardwareServiceUnavailableException {
        rocketHardwareProxy.stageRocket();
        rocketHardwareProxy.activateCurrentStage();
    }

    @Override
    public void launchRocket() throws TelemetryServiceUnavailableException, RocketHardwareServiceUnavailableException,
            InterruptedException {
        for (int i = 60; i > 10; i-=10) {
            LoggerHelper.logInfo("The mission will start in " + i + " seconds");
            sleep(500);
        }

        for (int i = 10; i >= 0; i--) {
            String unit = i > 1 ? " seconds" : " second";
            LoggerHelper.logInfo("The mission will start in " + i + unit);
            if (i == 3) {
                LoggerHelper.logInfo("Main engine starting");
                rocketHardwareProxy.activateCurrentStage();
            }
            sleep(500);
        }
        LoggerHelper.logInfo("Rocket launched");
        dataAsker.getNotificationOnEvents();
    }

    @Override
    public void slowDownRocket() throws RocketHardwareServiceUnavailableException {
        rocketHardwareProxy.slowDown();
        LoggerHelper.logInfo("Ask rocket-hardware to slow down");
    }

    @Override
    public void activeStage() throws RocketHardwareServiceUnavailableException {
        rocketHardwareProxy.activateCurrentStage();
        LoggerHelper.logInfo("Ask rocket-hardware to speed up");
    }
}
