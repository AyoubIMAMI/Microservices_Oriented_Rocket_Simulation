package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.controllers.dto.RobotDataDTO;
import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import fr.teama.telemetryservice.exceptions.*;
import org.springframework.http.ResponseEntity;

public interface DataSaver {
    ResponseEntity<String> saveRocketData(RocketDataDTO rocketDataDTO) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException;

    ResponseEntity<String> saveStageData(StageDataDTO stageDataDTO) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException, RobotDepartmentServiceUnavailableException;

    ResponseEntity<String> resetTracking();

    ResponseEntity<String> changeRocketName(String rocketName);

    ResponseEntity<String> saveRobotData(RobotDataDTO robotDataDTO) throws RocketStageServiceUnavailableException, ExecutiveServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, RobotDepartmentServiceUnavailableException;
}
