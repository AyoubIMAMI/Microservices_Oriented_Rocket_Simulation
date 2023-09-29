package fr.teama.rocketdepartmentservice.interfaces.proxy;

import fr.teama.rocketdepartmentservice.connectors.externalDTO.OperationTypeDTO;
import fr.teama.rocketdepartmentservice.connectors.externalDTO.TrackingFieldDTO;
import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;

public interface ITelemetryProxy {
    void askWhenEventHappens(TrackingFieldDTO fieldToTrack, Double data, String service, String routeToNotify,
                             OperationTypeDTO operationTypeDTO, String log) throws TelemetryServiceUnavailableException;

}
