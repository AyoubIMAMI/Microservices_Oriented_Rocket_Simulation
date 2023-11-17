package fr.teama.robotdepartmentservice.components;

import fr.teama.robotdepartmentservice.connectors.externalDTO.PositionDTO;
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
    public void startRobot(PositionDTO position) throws RobotHardwareServiceUnavailableException {
        robotHardwareProxy.moveToTheSpot(position.getX(), position.getY());
        kafkaProducerService.askRobotPosition(position.getX(), position.getY());
    }
    @Override
    public void takeSamples() throws RobotHardwareServiceUnavailableException {
        robotHardwareProxy.takeSamples();
    }

}
