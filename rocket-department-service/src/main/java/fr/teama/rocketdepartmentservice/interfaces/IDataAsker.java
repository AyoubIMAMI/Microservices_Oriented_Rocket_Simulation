package fr.teama.rocketdepartmentservice.interfaces;


import fr.teama.rocketdepartmentservice.exceptions.TelemetryServiceUnavailableException;

public interface IDataAsker {
    void waitEmptyFuelForStageTheRocket() throws TelemetryServiceUnavailableException;
}
