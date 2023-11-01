package fr.teama.robothardwaremockservice.interfaces.proxy;


import fr.teama.robothardwaremockservice.exceptions.TelemetryServiceUnavailableException;
import fr.teama.robothardwaremockservice.models.RobotData;

public interface ITelemetryProxy {

    void sendRobotData(RobotData robotData) throws TelemetryServiceUnavailableException;

    void sendSampleData(RobotData robotData);
}
