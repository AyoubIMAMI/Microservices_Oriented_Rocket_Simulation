package fr.teama.rockethardwareservice.interfaces.proxy;

import fr.teama.rockethardwareservice.exceptions.RobotHardwareServiceUnavaibleException;
import fr.teama.rockethardwareservice.models.Position;

public interface IRobotHardwareProxy {


    void startRobotLogging(Position position) throws RobotHardwareServiceUnavaibleException;
}
