package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.controllers.dto.RobotDataDTO;
import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import org.springframework.http.ResponseEntity;

public interface DataSaver {
    ResponseEntity<String> saveRocketData(RocketDataDTO rocketDataDTO);

    ResponseEntity<String> saveStageData(StageDataDTO stageDataDTO);

    ResponseEntity<String> resetTracking();

    ResponseEntity<String> changeRocketName(String rocketName);

    ResponseEntity<String> saveRobotData(RobotDataDTO robotDataDTO);

    ResponseEntity<String> saveRobotDataSample(RobotDataDTO robotDataDTO);
}
