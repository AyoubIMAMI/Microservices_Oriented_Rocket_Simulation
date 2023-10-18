package fr.teama.telemetryservice.interfaces;

import fr.teama.telemetryservice.controllers.dto.RocketDataDTO;
import fr.teama.telemetryservice.controllers.dto.StageDataDTO;
import fr.teama.telemetryservice.exceptions.ExecutiveServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.MissionServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.PayloadServiceUnavailableException;
import fr.teama.telemetryservice.exceptions.RocketStageServiceUnavailableException;
import org.springframework.http.ResponseEntity;

public interface DataSaver {
    ResponseEntity<String> saveRocketData(RocketDataDTO rocketDataDTO) throws PayloadServiceUnavailableException, RocketStageServiceUnavailableException, MissionServiceUnavailableException, ExecutiveServiceUnavailableException;

    ResponseEntity<String> saveStageData(StageDataDTO stageDataDTO) throws RocketStageServiceUnavailableException, MissionServiceUnavailableException, PayloadServiceUnavailableException, ExecutiveServiceUnavailableException;

    ResponseEntity<String> resetTracking();

    ResponseEntity<String> changeRocketName(String rocketName);
}
