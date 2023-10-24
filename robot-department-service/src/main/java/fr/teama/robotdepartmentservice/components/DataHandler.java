package fr.teama.robotdepartmentservice.components;

import fr.teama.robotdepartmentservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.robotdepartmentservice.interfaces.IDataAsker;
import fr.teama.robotdepartmentservice.interfaces.proxy.ITelemetryProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DataHandler implements IDataAsker {

    @Autowired
    private ITelemetryProxy telemetryProxy;


    @Override
    public void askDataToTelemetry() throws TelemetryServiceUnavailableException {
        telemetryProxy.askRocketHeight();
        telemetryProxy.askRobotHeight();
    }
}
