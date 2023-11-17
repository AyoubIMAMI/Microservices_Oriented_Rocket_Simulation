package fr.teama.robotdepartmentservice.interfaces;

import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;

public interface IRobotManager {

    void startRobot() throws RobotHardwareServiceUnavailableException;

    void takeSamples() throws RobotHardwareServiceUnavailableException;
}
