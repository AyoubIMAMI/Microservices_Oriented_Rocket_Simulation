package fr.teama.rocketdepartmentservice.components;

import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketdepartmentservice.helpers.LoggerHelper;
import fr.teama.rocketdepartmentservice.interfaces.IDataAsker;
import fr.teama.rocketdepartmentservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataAsker implements IDataAsker {
    @Autowired
    private ITelemetryProxy telemetryProxy;

    @Override
    public void waitEmptyFuelForStageTheRocket() throws TelemetryServiceUnavailableException {
        LoggerHelper.logInfo("The rocket want telemetry to notify him when the fuel is empty");
        telemetryProxy.gettingNotifyWhenFuelIsEmpty();
    }
}
