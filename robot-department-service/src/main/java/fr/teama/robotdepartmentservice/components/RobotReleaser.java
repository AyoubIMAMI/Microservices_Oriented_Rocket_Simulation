package fr.teama.robotdepartmentservice.components;

import fr.teama.robotdepartmentservice.exceptions.MissionServiceUnavailableException;
import fr.teama.robotdepartmentservice.exceptions.RocketHardwareServiceUnavailableException;
import fr.teama.robotdepartmentservice.interfaces.IRobotReleaser;
import fr.teama.robotdepartmentservice.interfaces.proxy.IMissionProxy;
import fr.teama.robotdepartmentservice.interfaces.proxy.IRocketHardwareProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RobotReleaser implements IRobotReleaser {

    @Autowired
    IMissionProxy missionProxy;

    @Autowired
    IRocketHardwareProxy rocketHardwareProxy;

    @Override
    public ResponseEntity<String> dropRobot() throws RocketHardwareServiceUnavailableException, MissionServiceUnavailableException {
        rocketHardwareProxy.dropRobot();
        missionProxy.missionSuccessNotify();
        return ResponseEntity.ok().body("Robot dropped");
    }
}
