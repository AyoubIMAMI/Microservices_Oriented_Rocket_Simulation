package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IDataAsker;
import fr.teama.rocketdepartmentservice.interfaces.IRocketAction;
import fr.teama.rocketdepartmentservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void launchRocket() throws TelemetryServiceUnavailableException, RocketHardwareServiceUnavailableException {
        rocketHardwareProxy.activateCurrentStage();
        LoggerHelper.logInfo("The rocket is launching");
        dataAsker.waitEmptyFuelForStageTheRocket();
    }
}
