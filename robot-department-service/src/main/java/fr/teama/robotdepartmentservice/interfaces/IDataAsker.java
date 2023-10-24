package fr.teama.robotdepartmentservice.interfaces;

import fr.teama.robotdepartmentservice.exceptions.TelemetryServiceUnavailableException;

public interface IDataAsker {

    void askDataToTelemetry() throws TelemetryServiceUnavailableException;
}
