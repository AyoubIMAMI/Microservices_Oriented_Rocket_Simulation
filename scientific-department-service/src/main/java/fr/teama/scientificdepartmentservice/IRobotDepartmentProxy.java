package fr.teama.scientificdepartmentservice;

import fr.teama.scientificdepartmentservice.exception.RobotDepartmentServiceUnavailableException;

public interface IRobotDepartmentProxy {

    void sendPositionToGo() throws RobotDepartmentServiceUnavailableException;
}
