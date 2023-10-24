package fr.teama.telemetryservice.interfaces.proxy;


import fr.teama.telemetryservice.exceptions.RobotDepartmentServiceUnavailableException;

public interface IRobotDepartmentProxy {

    void notifyHeightReached() throws RobotDepartmentServiceUnavailableException;

    void landedSuccessfully() throws RobotDepartmentServiceUnavailableException;
}
