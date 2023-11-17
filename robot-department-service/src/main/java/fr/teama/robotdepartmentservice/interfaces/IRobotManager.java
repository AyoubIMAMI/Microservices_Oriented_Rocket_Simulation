package fr.teama.robotdepartmentservice.interfaces;

import fr.teama.robotdepartmentservice.connectors.externalDTO.PositionDTO;
import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;

public interface IRobotManager {

    void startRobot(PositionDTO position) throws RobotHardwareServiceUnavailableException;

    void takeSamples() throws RobotHardwareServiceUnavailableException;
}
