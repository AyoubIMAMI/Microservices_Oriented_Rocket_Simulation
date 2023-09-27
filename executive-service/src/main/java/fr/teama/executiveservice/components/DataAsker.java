package fr.teama.executiveservice.components;

import fr.teama.executiveservice.interfaces.IDataAsker;
import fr.teama.executiveservice.interfaces.proxy.ITelemetryProxy;
import fr.teama.executiveservice.exceptions.TelemetryServiceUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DataAsker implements IDataAsker {

    @Autowired
    private ITelemetryProxy telemetryProxy;

    @Override
    public ResponseEntity<String> askStageHeightToTelemetry() throws TelemetryServiceUnavailableException {
        return telemetryProxy.missionStartNotify();
    }
}
