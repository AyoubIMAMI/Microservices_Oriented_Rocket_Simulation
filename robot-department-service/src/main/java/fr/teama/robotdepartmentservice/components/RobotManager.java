package fr.teama.robotdepartmentservice.components;

import fr.teama.robotdepartmentservice.exceptions.RobotHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.interfaces.IRobotManager;
import fr.teama.robotdepartmentservice.interfaces.proxy.IRobotHardwareProxy;
import fr.teama.robotdepartmentservice.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RobotManager implements IRobotManager {

    @Autowired
    IRobotHardwareProxy robotHardwareProxy;
    @Autowired
    KafkaProducerService kafkaProducerService;

    @Override
    public void startRobot() throws RobotHardwareServiceUnavailableException {
        //TODO change the coordinate from coordinate coming from scientific department
        robotHardwareProxy.moveToTheSpot(100.0,100.0);
        kafkaProducerService.askRobotPosition(100.0,100.0);
    }
    @Override
    public void takeSamples() throws RobotHardwareServiceUnavailableException {
        robotHardwareProxy.takeSamples();
    }

}
