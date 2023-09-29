package fr.teama.stagehardwaremockservice.interfaces.proxy;


import fr.teama.stagehardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.stagehardwaremockservice.models.StageData;

public interface ITelemetryProxy {

    void sendStageData(StageData stageData) throws TelemetryServiceUnavailableException;
}
