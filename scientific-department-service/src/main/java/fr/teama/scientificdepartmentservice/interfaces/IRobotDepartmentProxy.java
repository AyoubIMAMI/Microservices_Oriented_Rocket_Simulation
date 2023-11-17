package fr.teama.scientificdepartmentservice.interfaces;

import fr.teama.scientificdepartmentservice.exception.RobotDepartmentServiceUnavailableException;

public interface IRobotDepartmentProxy {

    void sendPositionToGo() throws RobotDepartmentServiceUnavailableException;
}
