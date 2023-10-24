package fr.teama.missionservice.interfaces.proxy;


import fr.teama.missionservice.exceptions.RobotDepartmentServiceUnavailableException;

public interface IRobotDepartmentProxy {

    void missionStartNotification() throws RobotDepartmentServiceUnavailableException;
}
