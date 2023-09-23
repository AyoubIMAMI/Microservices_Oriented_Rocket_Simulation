package fr.teama.rocketservice.components;

import fr.teama.rocketservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.rocketservice.interfaces.IDataAsker;
import fr.teama.rocketservice.interfaces.ILoggerComponent;
import fr.teama.rocketservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataAsker implements IDataAsker {
    @Autowired
    private ITelemetryProxy telemetryProxy;

    @Autowired
    ILoggerComponent logger;

    @Override
    public void waitEmptyFuelForStageTheRocket() throws TelemetryServiceUnavailableException {
        logger.logInfo("The rocket asked telemetry to notify him when the fuel is empty");
        telemetryProxy.gettingNotifyWhenFuelIsEmpty();
    }
}
