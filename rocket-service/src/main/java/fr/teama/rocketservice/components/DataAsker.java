package fr.teama.rocketservice.components;

import fr.teama.rocketservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketservice.helpers.LoggerHelper;
import fr.teama.rocketservice.interfaces.IDataAsker;
import fr.teama.rocketservice.interfaces.proxy.ITelemetryProxy;
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
