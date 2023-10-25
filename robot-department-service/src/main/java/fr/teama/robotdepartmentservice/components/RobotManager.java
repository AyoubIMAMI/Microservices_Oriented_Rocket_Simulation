package fr.teama.robotdepartmentservice.components;

import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.interfaces.IRobotManager;
import fr.teama.robotdepartmentservice.interfaces.proxy.IRobotHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RobotManager implements IRobotManager {

    @Autowired
    IRobotHardwareProxy robotHardwareProxy;

    @Override
    public void startRobot() throws RobotHardwareServiceUnavailableException {
        //TODO: stuff with scientific and others
        robotHardwareProxy.stopLogging();
    }
}
