package fr.teama.robotdepartmentservice.interfaces;

import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.exceptions.TelemetryServiceUnavailableException;

public interface IRobotManager {

    void startRobot() throws RobotHardwareServiceUnavailableException, TelemetryServiceUnavailableException;

    void takeSamples() throws RobotHardwareServiceUnavailableException;
}
